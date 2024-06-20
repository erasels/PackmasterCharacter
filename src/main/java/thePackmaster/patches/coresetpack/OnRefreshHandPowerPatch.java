package thePackmaster.patches.coresetpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import thePackmaster.powers.coresetpack.OnRefreshHandPower;

@SpirePatch(
        clz = CardGroup.class,
        method = "refreshHandLayout"
)
public class OnRefreshHandPowerPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(CardGroup __instance) {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnRefreshHandPower) {
                ((OnRefreshHandPower) p).onRefreshHand();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
