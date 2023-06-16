package thePackmaster.patches.lockonpack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import thePackmaster.actions.lockonpack.BruteforceAction;

@SpirePatch2(clz = LightningOrbEvokeAction.class, method = "update")
public class AoELightningPatch {
    public static void Prefix(LightningOrbEvokeAction __instance)
    {
        boolean hitAll = ((boolean)(ReflectionHacks.getPrivate(__instance, LightningOrbEvokeAction.class, "hitAll")) || BruteforceAction.aoeLightning);
        ReflectionHacks.setPrivate(__instance, LightningOrbEvokeAction.class, "hitAll", hitAll);
    }
}
