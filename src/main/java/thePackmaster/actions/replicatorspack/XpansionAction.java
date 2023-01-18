package thePackmaster.actions.replicatorspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.util.Wiz.att;

public class XpansionAction extends AbstractGameAction {
    private AbstractPlayer p;
    private boolean freeToPlayOnce;
    private int energyOnUse;

    public XpansionAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse) {
        this.actionType = ActionType.POWER;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            att(new ApplyPowerAction(p, p, new DuplicationPower(p, this.energyOnUse)));
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
