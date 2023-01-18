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

    public HoardPayoffAction(AbstractCard IN_ferno, Runnable payoff) {
        card = IN_ferno;
        this.payoff = payoff;
    }

    @Override
    public void update() {
        this.isDone = true;

        if (card.energyOnUse < EnergyPanel.totalCount) {
            card.energyOnUse = EnergyPanel.totalCount;
        }
        if (Wiz.p().hasRelic(ChemicalX.ID)) {
            card.energyOnUse = card.energyOnUse + 2;
        }
        if (HoardField.hoard.get(card) > 0) {
            HoardField.decrement(card, card.energyOnUse);
            if (HoardField.hoard.get(card) <= 0) {
                payoff.run();
                HoardField.resetValueToBase(card);
            }
        } else {
            payoff.run();
            HoardField.resetValueToBase(card);
        }
        if (!card.freeToPlayOnce) {
            AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        }
    }
}
