package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

import java.util.Collections;

public class BetterPowerNegationCheckPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CLASS
    )
    public static class Field {
        public static SpireField<Boolean> appliedSuccess = new SpireField<>(()->false);
    }

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class ReportSuccess {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void onApply(ApplyPowerAction __instance) {
            Field.appliedSuccess.set(__instance, true);
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher a = new Matcher.MethodCallMatcher(AbstractPower.class, "flash");
                Matcher b = new Matcher.MethodCallMatcher(Collections.class, "sort");
                int[] matchA = LineFinder.findInOrder(ctBehavior, a), matchB = LineFinder.findInOrder(ctBehavior, b);
                int[] combined = new int[matchA.length + matchB.length];
                System.arraycopy(matchA, 0, combined, 0, matchA.length);
                System.arraycopy(matchB, 0, combined, matchA.length, matchB.length);

                return combined;
            }
        }
    }
}
