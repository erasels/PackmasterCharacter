package thePackmaster.actions.summonspack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.orbs.summonspack.SwarmOfBees;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SummonBeesAction extends AbstractGameAction {
    private final boolean free;
    private final int energyOnUse;
    private final boolean upgrade;

    public SummonBeesAction(boolean freeToPlayOnce, int energyOnUse, boolean upgrade) {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
        free = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.upgrade = upgrade;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic(ChemicalX.ID)) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (upgrade)
            effect++;

        if (effect > 0) {
            for (int i = 0; i < effect; i++)
                atb(new ChannelAction(new SwarmOfBees()));

            if (!free)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
