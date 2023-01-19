package thePackmaster.patches.contentcreatorpack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import thePackmaster.powers.contentcreatorpack.FrostprimePower;

@SpirePatch(clz = ApplyPowerAction.class, method = "update")
public class CharacterTwoAmountPowerStackPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = { "p" })
    public static void stackAmount2(ApplyPowerAction __instance, AbstractPower p) {
        AbstractPower powerToApply = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
        if (p instanceof FrostprimePower && powerToApply instanceof FrostprimePower) {
            ((FrostprimePower)p).stackAmount2(((FrostprimePower)powerToApply).amount2);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPower.class, "stackPower");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}