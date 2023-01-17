package thePackmaster.actions.anomalypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class WonderAction extends AbstractGameAction {
    public WonderAction() {
        this.duration = 0.001F;
    }

    public void update() {
        this.tickDuration();
        if (this.isDone) {
            Iterator var1 = DrawCardAction.drawnCards.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                Wiz.atb(new LoseEnergyAction(Wiz.adp().energy.energy));
                Wiz.atb(new GainEnergyAction(c.cost));
            }

        }
    }
}
