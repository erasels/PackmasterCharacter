package thePackmaster.patches.enchanterpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import thePackmaster.cards.enchanterpack.AbstractEnchanterCard;
import thePackmaster.packs.EnchanterPack;
import thePackmaster.util.Wiz;

@SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
public class OnUseNeighborPatch {

    @SpireInsertPatch(locator = Locator.class)
    public static void onUseNeighbor(AbstractCard c) {
        if (Wiz.hand().contains(c)) {
            int index = Wiz.hand().group.indexOf(c);
            if (index > 0) {
                AbstractCard lNeighbor = Wiz.hand().group.get(index -1);
                if (lNeighbor instanceof AbstractEnchanterCard) {
                    ((AbstractEnchanterCard)lNeighbor).onPlayedNeighbor(c);
                }
            }
            if (index < Wiz.hand().size() - 1) {
                AbstractCard rNeighbor = Wiz.hand().group.get(index + 1);
                if (rNeighbor instanceof AbstractEnchanterCard) {
                    ((AbstractEnchanterCard)rNeighbor).onPlayedNeighbor(c);
                }
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(GameActionManager.class, "addToBottom");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
