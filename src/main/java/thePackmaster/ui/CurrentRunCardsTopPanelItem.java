package thePackmaster.ui;

import basemod.ReflectionHacks;
import basemod.TopPanelItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.MasterDeckViewScreen;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.TexLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.MAP;
import static thePackmaster.SpireAnniversary5Mod.selectedCards;

public class CurrentRunCardsTopPanelItem extends TopPanelItem {
    private static final float tipYpos = Settings.HEIGHT - (120.0f * Settings.scale);
    private static final float FLASH_ANIM_TIME = 2.0F;

    public static final String ID = SpireAnniversary5Mod.makeID("CurrentRunCardsTopPanelItem");
    private static final Texture IMAGE = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/CurrentRunCardsTopPanelItem.png"));
    private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    private static final String[] TEXT = STRINGS.TEXT;

    private static final Set<AbstractDungeon.CurrentScreen> validScreens = new HashSet<>();
    static {
        validScreens.add(COMBAT_REWARD);
        validScreens.add(MASTER_DECK_VIEW);
        validScreens.add(DEATH);
        validScreens.add(BOSS_REWARD);
        validScreens.add(SHOP);
        validScreens.add(MAP);
    }

    public static boolean open = false;
    private static final CardGroup poolGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public float flashTimer;

    public CurrentRunCardsTopPanelItem() {
        super(IMAGE, ID);
    }

    @Override
    public boolean isClickable() {
        return !AbstractDungeon.isScreenUp || validScreens.contains(AbstractDungeon.screen) || open;
    }

    @Override
    public void update() {
        updateFlash();
        super.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        boolean ic = isClickable();
        render(sb, ic? Color.WHITE : Color.DARK_GRAY);
        renderFlash(sb);
        renderHover(sb);
    }

    @Override
    protected void onClick() {
        if (isClickable()) {
            if (open && AbstractDungeon.screen == MASTER_DECK_VIEW) {
                AbstractDungeon.closeCurrentScreen();
                CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
                open = false;
            } else {
                if (!AbstractDungeon.isScreenUp) {
                    open();
                    return;
                }

                switch (AbstractDungeon.screen) {
                    case COMBAT_REWARD:
                        AbstractDungeon.previousScreen = AbstractDungeon.screen;
                        AbstractDungeon.closeCurrentScreen();
                        open();
                        break;
                    case MASTER_DECK_VIEW: //viewing master deck
                        AbstractDungeon.closeCurrentScreen();
                        open();
                        break;
                    case DEATH:
                        AbstractDungeon.previousScreen = AbstractDungeon.screen;
                        AbstractDungeon.deathScreen.hide();
                        open();
                        break;
                    case BOSS_REWARD:
                        AbstractDungeon.previousScreen = AbstractDungeon.screen;
                        AbstractDungeon.bossRelicScreen.hide();
                        open();
                        break;
                    case SHOP:
                        AbstractDungeon.overlayMenu.cancelButton.hide();
                        AbstractDungeon.previousScreen = AbstractDungeon.screen;
                        open();
                        break;
                    case MAP:
                        AbstractDungeon.previousScreen = AbstractDungeon.screen;
                        if (AbstractDungeon.dungeonMapScreen.dismissable) {
                            AbstractDungeon.closeCurrentScreen();
                        }
                        open();
                        break;
                }
            }
        }
    }

    private void open() {
        poolGroup.clear();

        for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
            List<AbstractCard> cards = new ArrayList<>(pack.cards);
            cards.sort((c1, c2) -> {
                if (c1.rarity != c2.rarity) {
                    return rarityToInt(c1.rarity) < rarityToInt(c2.rarity) ? -1 : 1;
                }
                return c1.name.compareTo(c2.name);
            });
            for (AbstractCard card : cards) {
                // Packs shouldn't have rarities other than common, uncommon, and rare in them, and if any cards sneak
                // through they still won't show up in the draft, so don't show them as part of the card pool
                if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.UNCOMMON || card.rarity == AbstractCard.CardRarity.RARE) {
                    poolGroup.addToTop(card);
                }
            }
        }

        open = true;
        CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
        AbstractDungeon.deckViewScreen.open();
    }

    private int rarityToInt(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case COMMON:
                return 0;
            case UNCOMMON:
                return 1;
            case RARE:
                return 2;
            default:
                return 3;
        }
    }

    public void flash() {
        this.flashTimer = FLASH_ANIM_TIME;
    }

    private void updateFlash() {
        if (flashTimer != 0.0f) {
            flashTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    public void renderHover(SpriteBatch sb) {
        if (this.getHitbox().hovered) {
            float xPos = this.x - this.hb_w;
            String packNames = SpireAnniversary5Mod.currentPoolPacks.stream().map(p -> p.name).collect(Collectors.joining(" NL "));
            String text = TEXT[1].replace("{0}", packNames);
            if (selectedCards) {
                TipHelper.renderGenericTip(xPos, tipYpos, TEXT[0], text);
            } else {
                TipHelper.renderGenericTip(xPos, tipYpos, TEXT[0], packNames); //if you haven't selected yet, don't tell the player the book is clickable
            }
        }
    }

    public void renderFlash(SpriteBatch sb) {
        float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, flashTimer / FLASH_ANIM_TIME);
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, flashTimer * FLASH_ANIM_TIME));

        float halfWidth = (float) this.image.getWidth() / 2.0F;
        float halfHeight = (float) this.image.getHeight() / 2.0F;
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float) this.image.getWidth(), (float) this.image.getHeight(), Settings.scale + tmp, Settings.scale + tmp, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float) this.image.getWidth(), (float) this.image.getHeight(), Settings.scale + tmp * 0.66F, Settings.scale + tmp * 0.66F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float) this.image.getWidth(), (float) this.image.getHeight(), Settings.scale + tmp / 3.0F, Settings.scale + tmp / 3.0F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);

        sb.setBlendFunction(770, 771);
    }




    /*----- Patches -----*/

    @SpirePatch(
            clz = TopPanel.class,
            method = "updateDeckViewButtonLogic"
    )
    public static class DefinitelyNotViewingPool
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "clickedDeckButton" }
        )
        public static void viewingDeck(TopPanel __instance, boolean clickedDeckButton)
        {
            if (clickedDeckButton)
            {
                if (open && AbstractDungeon.screen == MASTER_DECK_VIEW) {
                    AbstractDungeon.closeCurrentScreen();
                }
                open = false;
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(InputAction.class, "isJustPressed");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "updateControllerInput"
    )
    public static class ControllerUseAlt
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "deck" }
        )
        public static void viewAlt(MasterDeckViewScreen __instance, @ByRef(type="com.megacrit.cardcrawl.cards.CardGroup") Object[] deck)
        {
            if (open)
            {
                deck[0] = poolGroup;
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "updatePositions"
    )
    public static class UpdateAltPositions
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "cards" }
        )
        public static void updateAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards)
        {
            if (open)
            {
                cards[0] = poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "updateClicking"
    )
    public static class UpdateAltClick
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "hoveredCard", "clickStartedCard" }
        )
        public static SpireReturn<?> openAlt(MasterDeckViewScreen __instance, AbstractCard hovered, @ByRef(type="com.megacrit.cardcrawl.cards.AbstractCard") Object[] clickStartedCard)
        {
            if (open)
            {
                CardCrawlGame.cardPopup.open(hovered, poolGroup);
                clickStartedCard[0] = null;
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SingleCardViewPopup.class, "open");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "calculateScrollBounds"
    )
    public static class CalcAltBounds
    {
        @SpirePrefixPatch
        public static SpireReturn<?> calcAlt(MasterDeckViewScreen __instance)
        {
            if (open)
            {
                if (poolGroup.size() > 10) {
                    int scrollTmp = poolGroup.size() / 5 - 2;
                    if (poolGroup.size() % 5 != 0) {
                        ++scrollTmp;
                    }

                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT + (float)scrollTmp * (AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y));
                } else {
                    ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound", Settings.DEFAULT_SCROLL_LIMIT);
                }

                ReflectionHacks.setPrivate(__instance, MasterDeckViewScreen.class, "prevDeckSize", poolGroup.size());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "hideCards"
    )
    public static class HideAltCards
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "cards" }
        )
        public static void hideAlt(MasterDeckViewScreen __instance, @ByRef ArrayList<?>[] cards)
        {
            if (open)
            {
                cards[0] = poolGroup.group;
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "size");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "render"
    )
    public static class RenderAlt
    {
        @SpirePrefixPatch
        public static SpireReturn<?> rendering(MasterDeckViewScreen __instance, SpriteBatch sb)
        {
            if (open) {
                AbstractCard hoveredCard = ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "hoveredCard");
                if (hoveredCard == null) {
                    poolGroup.renderMasterDeck(sb);
                } else {
                    poolGroup.renderMasterDeckExceptOneCard(sb, hoveredCard);
                    hoveredCard.renderHoverShadow(sb);
                    hoveredCard.render(sb);
                }

                poolGroup.renderTip(sb);
                FontHelper.renderDeckViewTip(sb, TEXT[2], 96.0F * Settings.scale, Settings.CREAM_COLOR);
                if ((float)ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollUpperBound") > 500.0F * Settings.scale) {
                    ((ScrollBar)ReflectionHacks.getPrivate(__instance, MasterDeckViewScreen.class, "scrollBar")).render(sb);
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}