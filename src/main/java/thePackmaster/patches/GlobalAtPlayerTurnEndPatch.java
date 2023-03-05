package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import thePackmaster.patches.secretlevelpack.EnoughTalkPatch;


@SpirePatch(
        clz = GameActionManager.class,
        method = "callEndOfTurnActions"
)
public class GlobalAtPlayerTurnEndPatch {
    public static void Prefix(GameActionManager __instance) {
        EnoughTalkPatch.spokeLastTurn = false;
    }
}