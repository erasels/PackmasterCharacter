package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import thePackmaster.orbs.summonspack.OnApplyPowerOrb;

import static thePackmaster.util.Wiz.adp;

public class OnApplyPowerOrbPatch
{
    @SpirePatch(
            clz=ApplyPowerAction.class,
            method="update"
    )
    public static class ApplyPower
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"duration", "powerToApply"}
        )
        public static SpireReturn<Void> Insert(ApplyPowerAction __instance, @ByRef float[] duration, AbstractPower powerToApply)
        {
            if (__instance.source != null && __instance.source.isPlayer && __instance.target != null)
                for (AbstractOrb orb : adp().orbs)
                    if (orb instanceof OnApplyPowerOrb)
                        ((OnApplyPowerOrb) orb).onApplyPower(__instance.target, powerToApply);
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}