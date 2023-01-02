package thePackmaster.patches;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;

@SpirePatch(
        clz = NeowEvent.class,
        method = "update"
)
public class OpeningRunScreenPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void SetTheThing(NeowEvent __instance) {
        if (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
            BaseMod.logger.info("Packmaster is Ready To Do Thing");
            SpireAnniversary5Mod.openedStarterScreen = false;
            SpireAnniversary5Mod.doPackSetup = true;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(NeowEvent.class, "setPhaseToEvent");
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
        }
    }
}