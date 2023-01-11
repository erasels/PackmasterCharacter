package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import thePackmaster.orbs.summonspack.OnPlayCardOrb;

import static thePackmaster.util.Wiz.adp;

public class OnPlayCardOrbPatch {
    @SpirePatch2(
            clz = UseCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, AbstractCreature.class}
    )
    public static class OrbTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractCard card) {
            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof OnPlayCardOrb)
                    ((OnPlayCardOrb) orb).onPlayCard(card);
            }
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}