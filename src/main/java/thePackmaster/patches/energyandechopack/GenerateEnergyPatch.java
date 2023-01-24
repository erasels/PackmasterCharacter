package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.packs.EnergyAndEchoPack;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "gainEnergy"
)

public class GenerateEnergyPatch {
    @SpirePostfixPatch
    public static void postfix(AbstractPlayer __instance, int e) {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            EnergyAndEchoPack.generatedEnergy += e;
        }
    }
}

