package thePackmaster.vfx.gemspack;

import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.gemspack.AbstractGemsCard;
import thePackmaster.packs.GemsPack;

import java.util.ArrayList;

public class SocketGemEffect extends AbstractGameEffect {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("EnhanceBonfireOptions"));
        TEXT = uiStrings.TEXT;
    }

    public boolean openedScreen = false;
    public boolean gemSelect = false;
    public boolean socketSelect = false;
    public AbstractGemsCard gemChosen;
    private final Color screenColor;

    public SocketGemEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.updateBlackScreenColor();
        }

        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && gemSelect) {
            gemChosen = (AbstractGemsCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            gemSelect = false;
            socketSelect = true;
            GemsPack.gridScreenForGems = false;
            GemsPack.gridScreenForSockets = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.gridSelectScreen.open(getModifiableCards(), 1, TEXT[6], false, false, true, false);

        }

        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && socketSelect) {

            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            CardModifierManager.addModifier(c, gemChosen.myMod());

            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));

            if (gemChosen.upgraded) {
                gemChosen.downgrade();
            } else {
                AbstractDungeon.player.masterDeck.removeCard(gemChosen);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            ((RestRoom) AbstractDungeon.getCurrRoom()).fadeIn();
            GemsPack.gridScreenForSockets = false;
            AbstractDungeon.player.loseGold(GemsPack.goldCostToSocket);


        }

        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;
            this.gemSelect = true;
            GemsPack.gridScreenForGems = true;
            AbstractDungeon.gridSelectScreen.open(getGems(), 1, TEXT[5], false, false, true, false);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
                    RestRoom room = ((RestRoom) AbstractDungeon.getCurrRoom());
                    GemsPack.socketBonfireOption.reCheck();
                    boolean reinitialize = false;
                    ArrayList<AbstractCampfireOption> buttons = ReflectionHacks.getPrivate(room.campfireUI, CampfireUI.class, "buttons");
                    for (AbstractCampfireOption option : buttons) {
                        if (option instanceof SmithOption && option.usable && !(AbstractDungeon.player.masterDeck.getUpgradableCards().size() > 0 && !ModHelper.isModEnabled("Midas"))) {
                            reinitialize = true;
                        }
                    }
                    // In theory, we could always reinitialize the buttons since it shouldn't have side effects and it
                    // would catch other cases where button usability changed (instead of just the case of socketing the
                    // last upgradeable card in your deck). But it's possible other mods add undesirable side effects,
                    // so only call this method when we know we need to fix the upgrade option to reduce the chance of
                    // weird issues.
                    if (reinitialize) {
                        buttons.clear();
                        ReflectionHacks.privateMethod(CampfireUI.class, "initializeButtons").invoke(room.campfireUI);
                    }
                    room.campfireUI.reopen();
                    // there was a bug with the fire sound persisting and I'm not sure why,
                    // so this is basically a randomly thrown out preventative measure.
                    room.cutFireSound();

                }
            }

        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float) Settings.HEIGHT);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.gridSelectScreen.render(sb);
        }

    }

    public void dispose() {
    }

    public static CardGroup getGems() {
        CardGroup cg = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group
        ) {
            if (c instanceof AbstractGemsCard) {
                cg.addToBottom(c);
            }
        }
        return cg;
    }

    public static CardGroup getModifiableCards() {
        CardGroup cg = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group
        ) {
            if (!(c instanceof AbstractGemsCard)) {
                if (!c.hasTag(SpireAnniversary5Mod.ISCARDMODIFIED) && c.type != AbstractCard.CardType.CURSE && c.cost >= 1) {
                    cg.addToBottom(c);
                }
            }
        }
        return cg;
    }
}
