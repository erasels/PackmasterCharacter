package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.packs.EnergyAndEchoPack;
import thePackmaster.powers.energyandechopack.ReceptorPower;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "gainEnergy"
)

public class GenerateEnergyPatch {
    @SpirePostfixPatch
    public static void postfix(AbstractPlayer __instance, int e) {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            EnergyAndEchoPack.generatedEnergy += e;

            AbstractPower pow = AbstractDungeon.player.getPower(ReceptorPower.POWER_ID);
            if (pow != null) {
                for (int i = 0; i < e; i++)
                    pow.onSpecificTrigger();
            }
        }
    }
}

