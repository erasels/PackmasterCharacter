package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import thePackmaster.cards.warlockpack.Imp;

public class ResetImpDamageBonusPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")
    public static class StartOfCombatPatch {
        @SpirePrefixPatch
        public static void startOfCombat(AbstractPlayer __instance) {
            Imp.ImpDamageBonus = 0;
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "onVictory")
    public static class EndOfCombatPatch {
        @SpirePostfixPatch
        public static void endOfCombat(AbstractPlayer __instance) {
            Imp.ImpDamageBonus = 0;
        }
    }
}