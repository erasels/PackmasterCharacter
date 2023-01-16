package thePackmaster.patches.creativitypack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.creativitypack.StrikingStrike;

@SpirePatch(clz = PerfectedStrike.class, method = "countCards")
public class StrikingStrikePatch {

    public static int Postfix(int __result)
    {
        for (AbstractCard c : AbstractDungeon.player.hand.group)
        {
            if (c instanceof StrikingStrike) {
                __result++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group)
        {
            if (c instanceof StrikingStrike) {
                __result++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.discardPile.group)
        {
            if (c instanceof StrikingStrike) {
                __result++;
            }
        }

        return __result;
    }

}
