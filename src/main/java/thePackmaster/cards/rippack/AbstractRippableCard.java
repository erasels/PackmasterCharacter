package thePackmaster.cards.rippack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import thePackmaster.actions.rippack.DividedFuryAction;
import thePackmaster.actions.rippack.RipCardAction;
import thePackmaster.powers.rippack.DividedFuryPower;
import thePackmaster.vfx.rippack.ShowCardAndRipEffect;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public abstract class AbstractRippableCard extends AbstractRipCard {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Rip"));
    private AbstractGameAction action;
    protected ArrayList<AbstractCard> rippedParts;
    private static ArrayList<TooltipInfo> consumableTooltip;
    public static int cardsRippedThisTurn;

    public AbstractRippableCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/rip/" + type.name().toLowerCase() + "-rippable.png",
                "anniv5Resources/images/1024/rip/" + type.name().toLowerCase() + "-rippable.png"
        );
    }

    protected void setRippedCards(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        rippedParts = new ArrayList<>();
        if(upgraded) {
            artCard.upgrade();
            textCard.upgrade();
        }
        rippedParts.add(artCard);
        rippedParts.add(textCard);
    }

    public ArrayList<AbstractCard> getRippedParts() {
        return rippedParts;
    }

    public void onRightClick() {
        if(action == null) {
            if (canRip()) {
                att(new SFXAction("MAP_CLOSE"));
                action = new RipCardAction(this, rippedParts.get(0), rippedParts.get(1));
                att(action);
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                att(new VFXAction(new ShowCardAndRipEffect(this)));
            } else{
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0F, uiStrings.TEXT[1], true));
            }
        }
    }

    //For use when needing an upgraded version of the source card for `use`
    //If we try to upgrade the parts here too, you Stack Overflow
    public void upgradeJustSource() {
        super.upgrade();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        rippedParts.get(0).upgrade();
        rippedParts.get(1).upgrade();
    }

    @Override
    public void update() {
        super.update();
        if (action != null && action.isDone) {
            action = null;
        }
        if (AbstractDungeon.player != null) {
            clickUpdate();
        }
    }

    public boolean canRip() {
        return AbstractDungeon.player.hand.size() != BaseMod.MAX_HAND_SIZE;
    }

    public void onRip() {
        cardsRippedThisTurn++;
        AbstractPower pow = AbstractDungeon.player.getPower(DividedFuryPower.POWER_ID);
        if(pow != null) {
            atb(new DividedFuryAction(this));
        }
    }


    @Override
    public void atTurnStart() {
        super.atTurnStart();
        cardsRippedThisTurn = 0;
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(uiStrings.TEXT[0]);
        return retVal;
    }

    public void clickUpdate() {
        if (!AbstractDungeon.isScreenUp && HitboxRightClick.rightClicked.get(this.hb) && !AbstractDungeon.actionManager.turnHasEnded) {
            onRightClick();
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (consumableTooltip == null)
        {
            consumableTooltip = new ArrayList<>();
            consumableTooltip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("rippable")), BaseMod.getKeywordDescription(makeID("rippable"))));
        }
        List<TooltipInfo> compoundList = new ArrayList<>(consumableTooltip);
        if (super.getCustomTooltipsTop() != null) compoundList.addAll(super.getCustomTooltipsTop());
        return compoundList;
    }
}
