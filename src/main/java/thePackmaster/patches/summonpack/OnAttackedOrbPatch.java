package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import thePackmaster.orbs.summonspack.OnAttackedOrb;

import static thePackmaster.util.Wiz.adp;

public class OnAttackedOrbPatch {
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class OrbTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(DamageInfo info) {
            if (info == null)
                return;
            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof OnAttackedOrb)
                    ((OnAttackedOrb) orb).onAttacked(info);
            }
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractCreature.class, "powers");
                int[] x = LineFinder.findAllInOrder(behavior, matcher);
                return new int[]{x[1]};
            }
        }
    }
}