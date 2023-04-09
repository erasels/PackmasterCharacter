package thePackmaster.patches.summonerspellspack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.powers.summonerspellspack.CleansingFountainPower;

public class CleansingFountainPatch {

    @SpirePatch(
            clz = ArtifactPower.class,
            method = "onSpecificTrigger"
    )
    public static class CleansePatch
    {
        public static SpireReturn<Void> Prefix(ArtifactPower __instance)
        {
            if (!__instance.owner.hasPower(CleansingFountainPower.POWER_ID))
                return SpireReturn.Continue();
            __instance.owner.getPower(CleansingFountainPower.POWER_ID).flash();
            return SpireReturn.Return();
        }
    }
}
