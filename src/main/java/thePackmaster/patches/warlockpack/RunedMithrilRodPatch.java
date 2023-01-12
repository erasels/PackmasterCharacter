package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import thePackmaster.cards.warlockpack.RunedMithrilRod;

@SpirePatch(
    clz = AbstractPlayer.class,
    method = "draw",
    paramtypez = { int.class }
)
public class RunedMithrilRodPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = { "c" })
    public static void onCardDrawWhileInHand(AbstractPlayer __instance, int n, AbstractCard c) {
        for (AbstractCard card : __instance.hand.group) {
            if (card != c && card instanceof RunedMithrilRod) {
                ((RunedMithrilRod)card).onCardDrawWhileInHand();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "removeTopCard");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}