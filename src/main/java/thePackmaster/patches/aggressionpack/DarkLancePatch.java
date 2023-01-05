package thePackmaster.patches.aggressionpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class DarkLancePatch {
    public static boolean changedStancesThisTurn = false;

    @SpirePatch(clz = AbstractPlayer.class, method = "switchedStance", paramtypez = {})
    public static class SetChangedStancesThisTurnPatch {
        @SpirePostfixPatch
        public static void setChangedStancesThisTurn(AbstractPlayer __instance) {
            changedStancesThisTurn = true;
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")
    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnRelics")
    public static class ResetChangedStancesThisTurnPatch {
        @SpirePostfixPatch
        public static void resetChangedStancesThisTurn(AbstractPlayer __instance) {
            changedStancesThisTurn = false;
        }
    }
}
