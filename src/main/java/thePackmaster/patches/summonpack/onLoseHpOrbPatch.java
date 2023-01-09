package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import thePackmaster.orbs.summonspack.OnLoseHpOrb;

import static thePackmaster.util.Wiz.adp;

public class onLoseHpOrbPatch {
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class OprahWinfrey {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars= {"damageAmount"}
        )
        public static void Insert(int damageAmount) {
            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof OnLoseHpOrb)
                    ((OnLoseHpOrb) orb).onLoseHp(damageAmount);
            }
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(DamageInfo.class, "type");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
