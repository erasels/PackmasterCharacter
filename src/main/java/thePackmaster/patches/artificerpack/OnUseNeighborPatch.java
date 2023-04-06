package thePackmaster.patches.artificerpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import thePackmaster.cards.artificerpack.AbstractArtificerCard;
import thePackmaster.util.Wiz;

@SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
public class OnUseNeighborPatch {

    @SpireInsertPatch(locator = Locator.class)
    public static void onUseNeighbor(AbstractCard c, AbstractMonster monster) {
        if (Wiz.hand().contains(c)) {
            int index = Wiz.hand().group.indexOf(c);
            if (index > 0) {
                AbstractCard lNeighbor = Wiz.hand().group.get(index -1);
                if (lNeighbor instanceof AbstractArtificerCard) {
                    ((AbstractArtificerCard)lNeighbor).onPlayedNeighbor(c,monster);
                }
            }
            if (index < Wiz.hand().size() - 1) {
                AbstractCard rNeighbor = Wiz.hand().group.get(index + 1);
                if (rNeighbor instanceof AbstractArtificerCard) {
                    ((AbstractArtificerCard)rNeighbor).onPlayedNeighbor(c,monster);
                }
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "use");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
