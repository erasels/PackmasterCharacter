package thePackmaster.patches.arcanapack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz = GameActionManager.class,
        method = "incrementDiscard"
)
public class DiscardPowerPatch {
    public interface OnDiscardPower {
        void onManualDiscardPower();
    }

    @SpirePostfixPatch
    public static void onManualDiscard(boolean endOfTurn) {
        if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnDiscardPower) {
                    ((OnDiscardPower) p).onManualDiscardPower();
                }
            }
        }
    }
}
