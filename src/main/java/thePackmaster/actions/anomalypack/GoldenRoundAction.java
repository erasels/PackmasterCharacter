package thePackmaster.actions.anomalypack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ReboundPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import thePackmaster.cards.anomalypack.GoldenGun;


public class GoldenRoundAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard c;

    public GoldenRoundAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.c = c;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        boolean done = false;

        for (AbstractCard card : p.hand.group) {
            if (card instanceof GoldenGun) {
                if (!((GoldenGun) card).loaded()) {
                    done = true;
                    ((GoldenGun) card).load();
                    break;
                }
            }
        }

        if (!done) {
            for (AbstractGameAction act : AbstractDungeon.actionManager.actions) {
                if (act instanceof UseCardAction) {
                    UseCardAction action = (UseCardAction) act;
                    AbstractCard tempTargetCard = ReflectionHacks.getPrivate(action, UseCardAction.class, "targetCard");
                    if (tempTargetCard == this.c) {
                        action.reboundCard=true;
                        break;
                    }
                }
            }
        }

        isDone = true;
    }
}
