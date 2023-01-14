package thePackmaster.patches.psychicpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(
        clz = GameActionManager.class,
        method = "callEndOfTurnActions"
)
public class EndTurnPatch {
    @SpirePostfixPatch
    public static void theTurnIsNowOver(GameActionManager __instance) {
        DeepDreamPatch.triggerEndOfTurn();
    }
}
