package thePackmaster.patches.secretlevelpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EnoughTalkPatch {
    public static boolean spokeLastTurn = false;

    @SpirePatch(clz = TalkAction.class, method = "update")
    public static class CatchTalking {
        public static void Postfix(TalkAction __instance) {
            if (__instance.isDone) {
                if (__instance.source != AbstractDungeon.player) {
                    spokeLastTurn = true;
                }
            }
        }
    }

    @SpirePatch(clz = ShoutAction.class, method = "update")
    public static class CatchShouting {
        public static void Postfix(ShoutAction __instance) {
            if (__instance.isDone) {
                if (__instance.source != AbstractDungeon.player) {
                    spokeLastTurn = true;
                }
            }
        }
    }
}
