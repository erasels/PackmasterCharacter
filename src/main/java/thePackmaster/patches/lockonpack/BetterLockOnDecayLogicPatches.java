package thePackmaster.patches.lockonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LockOnPower;
import javassist.*;

import java.util.ArrayList;

public class BetterLockOnDecayLogicPatches {
    @SpirePatch(
            clz = LockOnPower.class,
            method = SpirePatch.CLASS
    )
    public static class Fields {
        public static SpireField<Boolean> justApplied = new SpireField<>(()->false);
    }

    @SpirePatch(clz = LockOnPower.class, method= SpirePatch.CONSTRUCTOR)
    public static class CaptureJustApplied {
        @SpireRawPatch
        public static void patch(CtBehavior ctMethodToPatch) throws NotFoundException, CannotCompileException {
            CtClass ctClass = ctMethodToPatch.getDeclaringClass();

            CtMethod method = CtNewMethod.make(
                    CtClass.voidType, // Return
                    "onInitialApplication", // Method name
                    new CtClass[]{}, //Paramters
                    null, // Exceptions
                    "{" +
                            CaptureJustApplied.class.getName() + ".setAppliedOnMonsterTurn(this);" +
                    "}",
                    ctClass
            );
            ctClass.addMethod(method);
        }

        public static void setAppliedOnMonsterTurn(LockOnPower lp) {
            if(AbstractDungeon.actionManager.turnHasEnded) {
                Fields.justApplied.set(lp, true);
            }
        }
    }

    @SpirePatch2(clz = LockOnPower.class, method = "atEndOfRound")
    public static class JustAppliedLogic {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<?> patch(LockOnPower __instance) {
            if(Fields.justApplied.get(__instance)) {
                Fields.justApplied.set(__instance, false);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.NewExprMatcher(ReducePowerAction.class);
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }
}
