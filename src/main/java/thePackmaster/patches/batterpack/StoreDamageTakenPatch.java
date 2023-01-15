package thePackmaster.patches.batterpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import thePackmaster.cards.batterpack.UltimateHomerun;

@SpirePatch(
        clz = AbstractMonster.class,
        method="damage"
)
public class StoreDamageTakenPatch{
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"damageAmount"}
    )
    public static void SetTheThing(AbstractMonster __instance, DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL)
        UltimateHomerun.HIGH_SCORE = Math.max(UltimateHomerun.HIGH_SCORE, Math.min(damageAmount, __instance.currentHealth));
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "lastDamageTaken");
            return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
