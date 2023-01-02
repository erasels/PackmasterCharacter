package thePackmaster.patches.ringofpainpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import thePackmaster.cards.ringofpainpack.Owl;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToExhaustPile",
        paramtypez={
                AbstractCard.class,
        }

)
// A patch to make Owl work
public class OnExhaustPatch {
    @SpireInsertPatch(locator = OnExhaustPatch.Locator.class)
    public static void TriggerOnExhaust(CardGroup instance, AbstractCard c) {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof Owl) {
                ((Owl) card).triggerEvolveOnExhaust();
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerOnExhaust");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}