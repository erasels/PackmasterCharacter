package thePackmaster.patches.sneckopack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;


public class EnergyCountPatch {

    public static int energySpentThisCombat;

    @SpirePatch2(clz = EnergyPanel.class, method = "useEnergy")
    public static class CountEnergy {
        @SpirePostfixPatch
        public static void countEnergySpent(int e) {
            energySpentThisCombat += e;
        }
    }


}
