package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.LoopPower;
import javassist.CtBehavior;
import thePackmaster.orbs.AbstractPackMasterOrb;

import static thePackmaster.util.Wiz.adp;

public class LoopPatch {
    @SpirePatch2(
            clz = LoopPower.class,
            method = "atStartOfTurn"
    )
    public static class LoopDeLoop {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert() {
            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof AbstractPackMasterOrb)
                    ((AbstractPackMasterOrb) orb).passiveEffect();
            }
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractOrb.class, "onStartOfTurn");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}