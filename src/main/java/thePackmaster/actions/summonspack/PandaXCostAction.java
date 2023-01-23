package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Panda;
import thePackmaster.vfx.summonspack.PandaExitEffect;

import static thePackmaster.util.Wiz.*;

public class PandaXCostAction extends AbstractGameAction {
    private static final float DURATION = 1.0F;
    private final boolean freeToPlay;
    private final int energyOnUse;
    private final boolean upgraded;


    public PandaXCostAction(boolean freeToPlay, int energyOnUse, boolean upgraded) {
        this.freeToPlay = freeToPlay;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        actionType = ActionType.SPECIAL;
        duration = DURATION;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic(ChemicalX.ID)) {
            effect += 2;
            adp().getRelic(ChemicalX.ID).flash();
        }

        if (upgraded)
            effect += 1;

        if (effect > 0) {
            for(int i = 0; i < effect; ++i)
                atb(new ChannelAction(new Panda()));

            if (!freeToPlay)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
