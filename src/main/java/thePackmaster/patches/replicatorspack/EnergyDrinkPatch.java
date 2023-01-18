package thePackmaster.patches.replicatorspack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import thePackmaster.powers.replicatorspack.EnergyDrinkPower;
import thePackmaster.powers.replicatorspack.IterativeDesignPower;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class EnergyDrinkPatch {
    @SpirePatch(clz = EmptyDeckShuffleAction.class, method = SpirePatch.CONSTRUCTOR)
    public static class GainDuplicationOnShuffle {
        @SpirePrefixPatch
        public static void onShuffle(EmptyDeckShuffleAction __instance) {
            AbstractPlayer p = adp();
            if (p.hasPower((EnergyDrinkPower.POWER_ID))) {
                int shuffleAmount = p.discardPile.size();
                int powerAmount = Wiz.adp().getPower(EnergyDrinkPower.POWER_ID).amount;
                int amount = powerAmount * (shuffleAmount/10);
                atb(new ApplyPowerAction(p, p, new DuplicationPower(p, amount)));
            }
        }
    }
}