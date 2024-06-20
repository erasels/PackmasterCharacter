package thePackmaster.actions.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        if (HoardField.hoard.get(card) <= 1){
            payoff.run();
            HoardField.resetValueToBase(card);
        } else {
            HoardField.decrement(card, 1);
        }
    }
}
