package thePackmaster.hats;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "dispose"
)
public class PlayerDisposeHatResetPatch {
    public static void Postfix(AbstractPlayer __instance) {
        if (__instance == AbstractDungeon.player) {
            Hats.playerHeadSlot = null;
        }
    }
}
