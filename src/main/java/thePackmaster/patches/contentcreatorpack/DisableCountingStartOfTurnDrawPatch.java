package thePackmaster.patches.contentcreatorpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class DisableCountingStartOfTurnDrawPatch {
    public static boolean DRAWN_DURING_TURN = false;

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            DRAWN_DURING_TURN = false;
            TrackCardsDrawnDuringTurnPatch.DRAWN_THIS_TURN = 0;
        }
    }
}
