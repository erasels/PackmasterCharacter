package thePackmaster.patches.Dragonwrath;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.dragonwrathpack.JudgementDayPower;

public class JudgementDayPatch {
    @SpirePatch2(clz = Lightning.class, method = "onEndOfTurn")
    public static class LightningAddedEffectPatch {
        @SpirePostfixPatch
        public static void patch(Lightning __instance) {
            AbstractPower p = AbstractDungeon.player.getPower(JudgementDayPower.POWER_ID);
            if (p != null) {
                p.onSpecificTrigger();
            }
        }
    }
}