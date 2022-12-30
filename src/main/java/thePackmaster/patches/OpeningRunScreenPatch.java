package thePackmaster.patches;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;

@SpirePatch(
        clz = NeowEvent.class,
        method = "update"
)
public class OpeningRunScreenPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void SetTheThing(NeowEvent __instance) {
        BaseMod.logger.info("Packmaster is Ready To Do Thing");
        SpireAnniversary5Mod.readyToDoThing = true;
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(NeowEvent.class, "setPhaseToEvent");
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
        }
    }
}