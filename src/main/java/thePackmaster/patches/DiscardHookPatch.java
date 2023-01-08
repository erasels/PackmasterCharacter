package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz = GameActionManager.class,
        method = "incrementDiscard"
)
public class DiscardHookPatch {
    public interface OnDiscardThing {
        void onManualDiscardThing(); //the weird name is mostly to avoid name conflicts
    }

    @SpirePostfixPatch
    public static void onManualDiscard(boolean endOfTurn) {
        if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnDiscardThing) {
                    ((OnDiscardThing) p).onManualDiscardThing();
                }
            }
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof OnDiscardThing) {
                    ((OnDiscardThing) orb).onManualDiscardThing();
                }
            }
        }
    }
}
