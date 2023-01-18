package thePackmaster.patches.replicatorspack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import thePackmaster.powers.replicatorspack.IterativeDesignPower;
import thePackmaster.util.Wiz;

//thanks ali
public class IterativeDesignPatch {
    @SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
    public static class GainBlockOnCardCreation {
        @SpirePostfixPatch
        public static void afterCardCreated(AbstractGameEffect __instance) {
            if (__instance.isDone == true) {
                AbstractPower p = Wiz.adp().getPower(IterativeDesignPower.POWER_ID);
                if(p!=null){
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(Wiz.adp(), p.amount));
                }
            }
        }
    }
}
