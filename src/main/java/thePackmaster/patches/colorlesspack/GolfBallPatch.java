package thePackmaster.patches.colorlesspack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.cards.colorlesspack.GolfBall;

public class GolfBallPatch {
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class SetGolfBallValue {

        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(GameActionManager __instance) {
            GolfBall.BLOCK_AMT_LOST = AbstractDungeon.player.currentBlock;
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasPower");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

}
