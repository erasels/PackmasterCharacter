package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.packs.EnergyAndEchoPack;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "useEnergy"
)

public class UsedEnergyPatch {
    public static void Prefix(int e) {
        EnergyAndEchoPack.usedEnergy += Math.min(EnergyPanel.totalCount, e);
    }
}

