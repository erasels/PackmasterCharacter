package thePackmaster.actions.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class AnticipationAction extends AbstractGameAction {
    public AnticipationAction() {
        this.duration = 0.001F;
    }

    public void update() {
        this.tickDuration();
        if (this.isDone) {
            Iterator var1 = DrawCardAction.drawnCards.iterator();
            int truecost = 0;

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                truecost += Wiz.getLogicalCardCost(c);
            }

            if (truecost > 0)
                Wiz.atb(new ApplyPowerAction(Wiz.p(), Wiz.p(), new StrengthPower(Wiz.p(), truecost), truecost));
        }
    }
}
