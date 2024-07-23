package thePackmaster.patches.replicatorspack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.replicatorspack.DuplicationPackmasterPower;
import thePackmaster.powers.replicatorspack.EnergyDrinkPower;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class EnergyDrinkPatch {
    public static int shuffled=0;
    @SpirePatch2(clz = Soul.class, method = "shuffle")
    public static class GainDuplicationOnDiscardShuffle {
        @SpirePrefixPatch
        public static void onDiscardShuffle() {
            AbstractPlayer p = adp();
            AbstractPower power = p.getPower(EnergyDrinkPower.POWER_ID);
            if (power!=null) {
                shuffled++;
                if(shuffled>=10){
                    atb(new ApplyPowerAction(p, p, new DuplicationPackmasterPower(p, power.amount)));
                    shuffled=0;
                }
            }
        }
    }

    @SpirePatch2(clz = CardGroup.class, method = "addToRandomSpot")
    public static class GainDuplicatioOnIntoDrawpileShuffle {
        @SpirePrefixPatch
        public static void onIntoDrawpileShuffle(CardGroup __instance) {
            if(!__instance.type.equals(CardGroup.CardGroupType.DRAW_PILE)){
                return;
            }
            AbstractPlayer p = adp();
            AbstractPower power = p.getPower(EnergyDrinkPower.POWER_ID);
            if (power!=null) {
                shuffled++;
                if(shuffled>=10){
                    atb(new ApplyPowerAction(p, p, new DuplicationPackmasterPower(p, power.amount)));
                    shuffled=0;
                }
            }
        }
    }
}