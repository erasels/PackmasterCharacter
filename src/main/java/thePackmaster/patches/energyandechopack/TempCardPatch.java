package thePackmaster.patches.energyandechopack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import thePackmaster.packs.EnergyAndEchoPack;
import thePackmaster.powers.energyandechopack.PanacherPower;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class TempCardPatch
{

    @SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
    public static class InDraw
    {
        public static void Prefix(AbstractGameEffect __instance)
        {
            if (__instance.duration == (float) ReflectionHacks.getPrivateStatic(__instance.getClass(), "EFFECT_DUR"))
            {
                //Panacher Power
                att(new AbstractGameAction() {//Action cause otherwise it's ConcurrentModificationException
                    @Override
                    public void update() {
                        AbstractPower pow2 = adp().getPower(PanacherPower.POWER_ID);
                        if (pow2 != null) {
                            pow2.onSpecificTrigger();
                        }
                        isDone = true;
                    }
                });
            }
        }
    }
}