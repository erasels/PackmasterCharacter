package thePackmaster.patches.Dragonwrath;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cards.dragonwrathpack.JudgementDay;
import thePackmaster.powers.dragonwrathpack.JudgementDayPower;

public class JudgementDayPatch {
    @SpirePatch2(clz = Lightning.class, method = "triggerPassiveEffect")
    public static class LightningAddedEffectPatch {
        @SpirePostfixPatch
        public static void Postfix() {
            if (AbstractDungeon.player.hasPower(JudgementDayPower.POWER_ID)) {
                AbstractDungeon.player.getPower(JudgementDayPower.POWER_ID).onSpecificTrigger();
            }
        }
    }
}