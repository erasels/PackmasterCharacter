package thePackmaster.patches.distortionpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "damage",
        paramtypez = { DamageInfo.class }
)
public class HPLostTrackingPatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = SpirePatch.CLASS
    )
    public static class Field {
        public static SpireField<Integer> hpLostTimes = new SpireField<>(()->0);

        public static void reset() {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                hpLostTimes.set(m, 0);
        }
    }

    @SpirePatch(
            clz = MonsterGroup.class,
            method = "applyEndOfTurnPowers"
    )
    public static class ResetTracking {
        @SpirePostfixPatch
        public static void reset(MonsterGroup __instance) {
            Field.reset();
        }
    }

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void OnHPLost(AbstractMonster __instance, DamageInfo info) {
        Field.hpLostTimes.set(__instance, Field.hpLostTimes.get(__instance) + 1);
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardCrawlGame.class, "overkill");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
