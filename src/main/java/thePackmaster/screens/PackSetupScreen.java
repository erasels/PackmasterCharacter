package thePackmaster.screens;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.ui.buttons.GridSelectConfirmButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.patches.compatibility.InfiniteSpirePatch;
import thePackmaster.summaries.PackSummaryDisplay;
import thePackmaster.ui.FixedModLabeledToggleButton.FixedModLabeledToggleButton;

import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.*;

public class PackSetupScreen extends CustomScreen {
    private static final Logger logger = LogManager.getLogger("PackSetup");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PackSetup"));
    private static final String[] TEXT = uiStrings.TEXT;

    private static final float CHOSEN_LABEL_Y = Settings.HEIGHT - (112.0F * Settings.scale);
    private static final float CHOSEN_PACK_Y = CHOSEN_LABEL_Y - (64.0F * Settings.scale) - (AbstractCard.IMG_HEIGHT * 0.3F);
    private static final float PACK_SELECT_Y = CHOSEN_PACK_Y - (AbstractCard.IMG_HEIGHT * 0.45F) - 200F * Settings.scale;
    private static final float PACK_SELECT_TEXT_Y = PACK_SELECT_Y - AbstractCard.IMG_HEIGHT * 0.45F - 60f * Settings.scale;

    private static final float SELECTED_SCALE = 0.6f;
    private static final float HOVER_SELECTED_SCALE = 0.75f;
    private static final float SELECTED_PACK_SPACING = AbstractCard.IMG_WIDTH * HOVER_SELECTED_SCALE + Settings.CARD_VIEW_PAD_X * 0.8f;

    private static final float SELECTING_SCALE = 0.8f;
    private static final float SELECTING_PACK_SPACING = AbstractCard.IMG_WIDTH * 0.8F + Settings.CARD_VIEW_PAD_X * 2;

    private static FixedModLabeledToggleButton summaryToggleButton;

    private Random rng;

    private PackSetupMode mode;
    private final List<AbstractCardPack> packPool = new ArrayList<>();
    private int packChoices;
    private final List<AbstractCardPack> choiceSet = new ArrayList<>();

    private final GridSelectConfirmButton confirmButton;

    private final Map<AbstractCardPack, Vector2> packPositionMap = new HashMap<>();
    private float transitionStartTime = 0f, transitionTime = 0f;

    private enum PackSetupMode {
        DRAFTING,
        TRANSITION_OUT_DRAFT,
        TRANSITION_TO_DRAFT,
        CONFIRMING
    }

    public PackSetupScreen() {
        rng = new Random(); //shouldn't be used, but I'd rather not leave it null

        confirmButton = new GridSelectConfirmButton(GridCardSelectScreen.TEXT[0]);
        confirmButton.isDisabled = false;
        confirmButton.hideInstantly();
    }


    public void open(int randomPacks, int packChoices) {
        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE)
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        AbstractDungeon.screen = curScreen();
        AbstractDungeon.isScreenUp = true;
        summaryToggleButton = new FixedModLabeledToggleButton(uiStrings.TEXT[2],
                Settings.WIDTH * 0.025f, Settings.HEIGHT * 0.05f,
                Settings.CREAM_COLOR, FontHelper.buttonLabelFont,
                showPackSummaries(),
                null, l -> {},
                b -> togglePackSummaries()
        );

        confirmButton.hideInstantly();

        AbstractDungeon.overlayMenu.showBlackScreen();

        rng = new Random(Settings.seed);

        packPool.clear();
        for (AbstractCardPack p : SpireAnniversary5Mod.allPacks) {
            if (!currentPoolPacks.contains(p)) {
                packPool.add(p);
            }
        }

        if (randomPacks > 0) {
            logger.info("Adding " + randomPacks + " random packs.");
            randomPacks(randomPacks);
        }

        this.packChoices = packChoices;
        if (packChoices > 0) {
            startChoice();
            transitionStartTime = transitionTime = 0.5f;
        }
        else {
            confirm();
        }

        initialPositionPacks();
    }

    @Override
    public void reopen() {
        AbstractDungeon.screen = curScreen();
        AbstractDungeon.isScreenUp = true;

        AbstractDungeon.overlayMenu.showBlackScreen();
    }

    @Override
    public void close() {
        for (AbstractCardPack pack : unfilteredAllPacks) {
            pack.previewPackCard.stopGlowing();
            pack.previewPackCard.noShadow();
        }

        summaryToggleButton = null;
    }

    @Override
    public void update() {
        updateTransition();
        updateControllerInput();

        summaryToggleButton.update();

        for (AbstractCardPack pack : currentPoolPacks) {
            pack.previewPackCard.stopGlowing();
            pack.previewPackCard.update();
            float curDrawScale = pack.previewPackCard.drawScale;
            pack.previewPackCard.updateHoverLogic();
            pack.previewPackCard.drawScale = curDrawScale;
            if (pack.previewPackCard.hb.hovered) {
                pack.previewPackCard.targetDrawScale = (mode == PackSetupMode.CONFIRMING) ? HOVER_SELECTED_SCALE * 1.1f : HOVER_SELECTED_SCALE;
            }
            else {
                pack.previewPackCard.targetDrawScale = (mode == PackSetupMode.CONFIRMING) ? HOVER_SELECTED_SCALE : SELECTED_SCALE;
            }

            if (pack.previewPackCard.hb.hovered) {
                displayTooltips(pack);
            }
            if (pack.previewPackCard.hb.justHovered) {
                CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4F);
            }

            if (mode == PackSetupMode.CONFIRMING || pack.previewPackCard.hb.hovered) {
                pack.previewPackCard.noShadow();
            }
            else {
                pack.previewPackCard.shadow();
            }
        }
        if (mode != PackSetupMode.CONFIRMING) {
            for (AbstractCardPack pack : choiceSet) {
                pack.previewPackCard.beginGlowing();
                pack.previewPackCard.update();
                pack.previewPackCard.updateHoverLogic();
                if (!pack.previewPackCard.hb.hovered) {
                    pack.previewPackCard.targetDrawScale = SELECTING_SCALE;
                }
                else {
                    displayTooltips(pack);
                }

                if (pack.previewPackCard.hb.justHovered) {
                    CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4F);
                }
            }
        }

        switch (mode) {
            case CONFIRMING:
                confirmButton.update();
                if (confirmButton.hb.clicked) {
                    confirmButton.hb.clicked = false;
                    genericScreenOverlayReset();
                    AbstractDungeon.closeCurrentScreen();

                    currentPoolPacks.sort(Comparator.comparing((pack)->pack.name));
                    SpireAnniversary5Mod.selectedCards = true;
                    editPotionPool(PotionHelper.potions);
                    CardCrawlGame.dungeon.initializeCardPools();
                    InfiniteSpirePatch.generateQuestsIfInfiniteSpireIsLoaded();
                }
                break;
            case DRAFTING:
                AbstractCardPack clicked = null;
                for (AbstractCardPack pack : choiceSet) {
                    if (pack.previewPackCard.hb.hovered && (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())) {
                        clicked = pack;
                        break;
                    }
                }
                if (clicked != null) {
                    choiceSet.remove(clicked);
                    insertPack(clicked);

                    if (packChoices > 0) {
                        mode = PackSetupMode.TRANSITION_OUT_DRAFT;
                        transitionTime = transitionStartTime = 0.4f;
                    }
                    else {
                        confirm();
                    }

                }
                break;
            case TRANSITION_TO_DRAFT:
                break;
        }
    }

    private void updateTransition() {
        if (transitionTime > 0) {
            transitionTime = Math.max(0, transitionTime - Gdx.graphics.getDeltaTime());
            float prog = 1 - (transitionTime / transitionStartTime);
            float packX;

            float chosenPackProg = Math.min(1, prog * 1.3f);
            packX = (Settings.WIDTH / 2f) - ((currentPoolPacks.size() - 1) / 2f) * SELECTED_PACK_SPACING;
            for (AbstractCardPack pack : currentPoolPacks) {
                setX(pack, Interpolation.circleOut.apply(packPositionMap.get(pack).x, packX, chosenPackProg));
                setY(pack, Interpolation.circleOut.apply(packPositionMap.get(pack).y, mode == PackSetupMode.CONFIRMING ? Settings.HEIGHT / 2f : CHOSEN_PACK_Y, chosenPackProg));
                packX += SELECTED_PACK_SPACING;
            }

            switch (mode) {
                case TRANSITION_OUT_DRAFT:
                    for (AbstractCardPack pack : choiceSet) {
                        setY(pack, Interpolation.circleOut.apply(packPositionMap.get(pack).y, -400 * Settings.scale, prog));
                    }
                    if (prog >= 1) {
                        if (packChoices > 0) {
                            startChoice();
                            transitionTime = transitionStartTime = Settings.FAST_MODE ? 0.5f : 0.8f;
                            savePackPositions();
                        }
                        else { //shouldn't get here
                            mode = PackSetupMode.CONFIRMING;
                            transitionTime = transitionStartTime = 0.4f;
                            savePackPositions();
                        }
                    }
                    break;
                case TRANSITION_TO_DRAFT:
                    packX = (Settings.WIDTH / 2f) - ((choiceSet.size() - 1) / 2f) * SELECTING_PACK_SPACING;
                    for (AbstractCardPack pack : choiceSet) {
                        setX(pack, Interpolation.linear.apply(packPositionMap.get(pack).x, packX, prog));
                        setY(pack, Interpolation.circleOut.apply(packPositionMap.get(pack).y, PACK_SELECT_Y, prog));
                        packX += SELECTING_PACK_SPACING;
                    }
                    if (prog >= 1)
                        mode = PackSetupMode.DRAFTING;
                    break;
                case DRAFTING:
                    break;
            }
        }
        else if (transitionStartTime > 0) {
            transitionStartTime = -1;
            savePackPositions();
        }
    }

    private void updateControllerInput() {
        if (Settings.isControllerMode && !AbstractDungeon.topPanel.selectPotionMode && AbstractDungeon.topPanel.potionUi.isHidden && !AbstractDungeon.player.viewingRelics) {// 165
            if (mode != PackSetupMode.DRAFTING || choiceSet.isEmpty())
                return;

            int index = -1;
            boolean anyHovered = false;

            for (AbstractCardPack pack : this.choiceSet) {
                ++index;
                if (pack.previewPackCard.hb.hovered) {
                    anyHovered = true;
                    break;
                }
            }

            if (!anyHovered) {
                index = 0;
                Gdx.input.setCursorPosition((int) this.choiceSet.get(index).previewPackCard.hb.cX, Settings.HEIGHT - (int) this.choiceSet.get(index).previewPackCard.hb.cY);
            } else if (!CInputActionSet.left.isJustPressed() && !CInputActionSet.altLeft.isJustPressed()) {
                if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                    ++index;
                    if (index >= this.choiceSet.size()) {
                        index = 0;
                    }

                    Gdx.input.setCursorPosition((int) this.choiceSet.get(index).previewPackCard.hb.cX, Settings.HEIGHT - (int) this.choiceSet.get(index).previewPackCard.hb.cY);
                }
            } else {
                --index;
                if (index < 0) {
                    index = this.choiceSet.size() - 1;
                }

                Gdx.input.setCursorPosition((int) this.choiceSet.get(index).previewPackCard.hb.cX, Settings.HEIGHT - (int) this.choiceSet.get(index).previewPackCard.hb.cY);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        summaryToggleButton.render(sb);
        for (AbstractCardPack pack : currentPoolPacks)
            pack.previewPackCard.render(sb);

        if (mode != PackSetupMode.CONFIRMING) {
            for (AbstractCardPack pack : choiceSet)
                pack.previewPackCard.render(sb);

            FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, TEXT[1], Settings.WIDTH / 2f, CHOSEN_LABEL_Y, Settings.CREAM_COLOR);
            FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, TEXT[0], Settings.WIDTH / 2f, PACK_SELECT_TEXT_Y, Settings.CREAM_COLOR);
        }

        confirmButton.render(sb);
    }

    private void confirm() {
        mode = PackSetupMode.CONFIRMING;
        transitionTime = transitionStartTime = 0.5f;

        for (AbstractCardPack pack : currentPoolPacks) {
            pack.previewPackCard.noShadow();
        }

        confirmButton.show();
    }

    @Override
    public void openingSettings() {
        AbstractDungeon.previousScreen = curScreen();
    }

    private void insertPack(AbstractCardPack pack) {
        pack.previewPackCard.shadow();

        int i = 0;
        for (; i < currentPoolPacks.size(); ++i) {
            if (pack.previewPackCard.target_x < currentPoolPacks.get(i).previewPackCard.target_x) {
                currentPoolPacks.add(i, pack);
                return;
            }
        }
        currentPoolPacks.add(pack);
    }

    private void initialPositionPacks() {
        for (AbstractCardPack pack : currentPoolPacks) {
            pack.previewPackCard.drawScale = pack.previewPackCard.targetDrawScale = SELECTED_SCALE;
            pack.previewPackCard.shadow();
            setY(pack, Settings.HEIGHT + 400 * Settings.scale);
        }
        updateSelectedPacksX();

        savePackPositions();
    }

    private void updateSelectedPacksX() {
        float packX = (Settings.WIDTH / 2f) - ((currentPoolPacks.size() - 1) / 2f) * SELECTED_PACK_SPACING;
        for (AbstractCardPack pack : currentPoolPacks) {
            setX(pack, packX);
            packX += SELECTED_PACK_SPACING;
        }
    }
    private void updateChoicePacksX() {
        float packX = (Settings.WIDTH / 2f) - ((choiceSet.size() - 1) / 2f) * SELECTING_PACK_SPACING;
        for (AbstractCardPack pack : choiceSet) {
            setX(pack, packX);
            packX += SELECTING_PACK_SPACING;
        }
    }

    private void savePackPositions() {
        packPositionMap.clear();
        for (AbstractCardPack pack : currentPoolPacks) {
            packPositionMap.compute(pack, (pak, pos)->{
                if (pos == null) {
                    return new Vector2(pak.previewPackCard.target_x, pak.previewPackCard.target_y);
                }
                pos.set(pak.previewPackCard.target_x, pak.previewPackCard.target_y);
                return pos;
            });
        }
        for (AbstractCardPack pack : choiceSet) {
            packPositionMap.compute(pack, (pak, pos)->{
                if (pos == null) {
                    return new Vector2(pak.previewPackCard.target_x, pak.previewPackCard.target_y);
                }
                pos.set(pak.previewPackCard.target_x, pak.previewPackCard.target_y);
                return pos;
            });
        }
    }
    private void setX(AbstractCardPack pack, float x) {
        pack.previewPackCard.target_x = pack.previewPackCard.current_x = x;
    }
    private void setY(AbstractCardPack pack, float y) {
        pack.previewPackCard.target_y = pack.previewPackCard.current_y = y;
    }

    private void startChoice() {
        logger.info(packChoices + " pack choices remaining.");
        mode = PackSetupMode.TRANSITION_TO_DRAFT;
        --packChoices;

        choiceSet.clear();
        while (choiceSet.size() < PACKS_PER_CHOICE && !packPool.isEmpty()) {
            AbstractCardPack target = packPool.remove(rng.random(packPool.size() - 1));
            target.previewPackCard.noShadow();
            choiceSet.add(target);
        }

        if (choiceSet.isEmpty()) {
            mode = PackSetupMode.CONFIRMING;
            return;
        }

        for (AbstractCardPack pack : choiceSet) {
            pack.previewPackCard.drawScale = pack.previewPackCard.targetDrawScale = SELECTING_SCALE;
            setY(pack, -400 * Settings.scale);
        }
        updateChoicePacksX();
    }

    private void randomPacks(int amount) {
        while (amount > 0 && !packPool.isEmpty()) {
            AbstractCardPack target = packPool.remove(rng.random(packPool.size() - 1));
            SpireAnniversary5Mod.logger.info("Randomly selected: " + target.packID);
            currentPoolPacks.add(target);
            --amount;
        }
    }

    public static void editPotionPool(ArrayList<String> potionList) {
        potionList.removeIf(potionID -> SpireAnniversary5Mod.packExclusivePotions.contains(potionID));

        Set<String> potionsToAdd = new HashSet<>();
        for (AbstractCardPack pack : currentPoolPacks) {
            potionsToAdd.addAll(pack.getPackPotions());
        }
        potionList.forEach(potionsToAdd::remove);
        potionList.addAll(potionsToAdd);
    }

    private static void displayTooltips(AbstractCardPack pack) {
        ArrayList<PowerTip> tooltips = new ArrayList<>();
        tooltips.add(new PowerTip(PackSummaryDisplay.getTitle(), PackSummaryDisplay.getTooltip(pack.summary)));
        if (pack.credits != null)
            tooltips.add(new PowerTip(pack.creditsHeader, pack.credits));
        if (!tooltips.isEmpty()) {
            // These values are taken from AbstractCard. We need to use them so we can calculate the position for the
            // tooltips based on the target scale that the card will end up at (since we're always hovering the card
            // when tooltips are displayed). This avoids jitter due to the height/width of the card changing.
            final float HB_W = 300.0F * Settings.scale;
            final float HB_H = 420.0F * Settings.scale;
            float x = pack.previewPackCard.hb.cX + HB_W * pack.previewPackCard.targetDrawScale / 2.0f;
            if(x + (float)ReflectionHacks.getPrivateStatic(TipHelper.class, "BOX_W") > Settings.WIDTH) {
                x = pack.previewPackCard.hb.cX - HB_W * pack.previewPackCard.targetDrawScale / 2.0f;
            }
            float y = pack.previewPackCard.hb.cY + HB_H * pack.previewPackCard.targetDrawScale / 2.0f;
            TipHelper.queuePowerTips(x, y, tooltips);
        }
    }

    public static class Enum
    {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen PACK_SETUP_SCREEN;
    }
    @Override
    public AbstractDungeon.CurrentScreen curScreen()
    {
        return Enum.PACK_SETUP_SCREEN;
    }
}
