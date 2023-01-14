package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.DoubleEnergy;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.packs.EnergyAndEchoPack;
import thePackmaster.powers.energyandechopack.DivineTouchPower;
import thePackmaster.powers.energyandechopack.PanacherPower;

import static thePackmaster.util.Wiz.adp;

public class OnCreateCardPatch {

    @SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "update")
    @SpirePatch(clz = MakeTempCardInHandAction.class, method = "update")
    public static class HandOrDiscard
    {

        public static void Prefix(AbstractGameAction __instance, float ___duration, float ___startDuration, @ByRef AbstractCard[] ___c)
        {
            if (___duration == ___startDuration) {

                //Divine Touch Power
                AbstractPower pow = adp().getPower(DivineTouchPower.POWER_ID);
                if (pow != null) {
                    if (pow.amount > EnergyAndEchoPack.cardsCreatedThisTurn) {
                        ___c[0] = new DoubleEnergy();
                    }
                }

                //Panacher Power
                AbstractPower pow2 = adp().getPower(PanacherPower.POWER_ID);
                if (pow2 != null) {
                    pow2.onSpecificTrigger();
                }

                EnergyAndEchoPack.cardsCreatedThisTurn++;
            }
        }

    }

    @SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
    public static class Draw
    {

        public static void Prefix(AbstractGameAction __instance, float ___duration, float ___startDuration, @ByRef AbstractCard[] ___cardToMake)
        {
            if (___duration == ___startDuration) {

                //Divine Touch Power
                AbstractPower pow = adp().getPower(DivineTouchPower.POWER_ID);
                if (pow != null) {
                    if (pow.amount > EnergyAndEchoPack.cardsCreatedThisTurn) {
                        ___cardToMake[0] = new DoubleEnergy();
                    }
                }

                //Panacher Power
                AbstractPower pow2 = adp().getPower(PanacherPower.POWER_ID);
                if (pow2 != null) {
                    pow2.onSpecificTrigger();
                }

                EnergyAndEchoPack.cardsCreatedThisTurn++;
            }
        }

    }


}

