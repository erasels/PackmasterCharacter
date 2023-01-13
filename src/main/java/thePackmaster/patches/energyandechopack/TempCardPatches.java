
package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.DoubleEnergy;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import thePackmaster.powers.energyandechopack.DivineTouchPower;

import static thePackmaster.util.Wiz.adp;

public class TempCardPatches
{

    @SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
    @SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
    public static class InDraw
    {
        public static void Prefix(AbstractGameEffect __instance, float ___EFFECT_DUR, AbstractCard ___card)
        {
            if (__instance.duration == ___EFFECT_DUR)
            {
                if (AbstractDungeon.getCurrRoom().monsters != null && !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
                    if (adp().hasPower(DivineTouchPower.POWER_ID)) {
                        if (adp().getPower(DivineTouchPower.POWER_ID).amount > 0) {
                            ___card = new DoubleEnergy();
                        }
                    }
            }
        }
    }
}