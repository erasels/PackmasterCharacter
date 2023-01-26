package thePackmaster.actions.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.util.Wiz;
import thePackmaster.util.cardvars.HoardField;

public class HoardPayoffAction extends AbstractGameAction {

    AbstractCard card = null;
    private Runnable payoff;

    public HoardPayoffAction(AbstractCard hoardCard, Runnable payoff) {
        card = hoardCard;
        this.payoff = payoff;
    }

    @Override
    public void update() {
        this.isDone = true;

        if (card.cost == -1) {
            if (card.energyOnUse < EnergyPanel.totalCount) {
                card.energyOnUse = EnergyPanel.totalCount;
            }
            if (Wiz.p().hasRelic(ChemicalX.ID)) {
                card.energyOnUse = card.energyOnUse + 2;
            }
        }

        if (HoardField.hoard.get(card) > 0) {
            if (card.cost == -1){
                HoardField.decrement(card, card.energyOnUse);
            } else {
                HoardField.decrement(card, Wiz.getLogicalCardCost(card));
            }
            if (HoardField.hoard.get(card) <= 0) {
                card.returnToHand = false;
                payoff.run();
                HoardField.resetValueToBase(card);
            } else {
                if ((card.cost == -1 && card.energyOnUse > 0) || (Wiz.getLogicalCardCost(card) > 0)) {
                    card.returnToHand = true;
                    card.retain = true;
                } else {
                    //If Hoard was used this turn, then the card is played later for 0-cost (most common on an X-Cost)
                    //then we should not be returning it to hand on this activation per how the keyword states it works.
                    card.returnToHand = false;
                }
            }
        } else {
            card.returnToHand = false;
            payoff.run();
            HoardField.resetValueToBase(card);
        }
        if (card.cost == -1) {
            if (!card.freeToPlayOnce) {
                AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
            }
        }
    }
}
