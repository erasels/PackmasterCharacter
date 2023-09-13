package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import thePackmaster.packs.EnergyAndEchoPack;
import thePackmaster.patches.odditiespack.AutoBattlerPatches;


@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class GlobalAtTurnStartHookPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(GameActionManager __instance) {
        EnergyAndEchoPack.resetvalues();
        AutoBattlerPatches.OnRefreshHandCheckToPlayCardPatch.isEndingTurn = false;
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}