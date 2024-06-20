package thePackmaster.patches.anomalypack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.anomalypack.MidnightOil;
import thePackmaster.util.Wiz;

import java.util.Iterator;

public class MidnightOilPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnPostDrawRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            Iterator var1 = AbstractDungeon.player.exhaustPile.group.iterator();
            int truecost = 0;

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (c instanceof MidnightOil)
                truecost += 1;
            }

            if (truecost > 0)
                Wiz.atb(new GainEnergyAction(truecost));
        }
    }
}
