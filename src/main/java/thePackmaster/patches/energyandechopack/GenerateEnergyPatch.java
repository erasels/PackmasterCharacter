package thePackmaster.patches.energyandechopack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.GainEnergyAndEnableControlsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.energyandechopack.Crystallize;
import thePackmaster.packs.EnergyAndEchoPack;

public class GenerateEnergyPatch {
    //This is needed because turnHasEnded will be true when start of combat relics trigger to give energy
    private static boolean workaround = false;

    @SpirePatch(clz = AbstractPlayer.class, method = "gainEnergy")
    public static class CatchEnergyGen {
        @SpirePostfixPatch
        public static void postfix(AbstractPlayer __instance, int e) {
            // Just gaining energy doesn't call applyPowers, so we have this to avoid card text being out of date
            // An example that was causing issues is the energy gain from Unrelenting Form
            for (int i = 0; i < AbstractDungeon.player.hand.group.size(); i++) {
                AbstractCard c = AbstractDungeon.player.hand.group.get(i);
                if (c instanceof Crystallize) {
                    c.applyPowers();
                }
            }
            if (!AbstractDungeon.actionManager.turnHasEnded || workaround) {
                EnergyAndEchoPack.generatedEnergy += e;
            }
        }
    }

    //Easier than patching into preBattlePrep because energypanel.prep happens there
    @SpirePatch2(clz=AbstractPlayer.class, method = "applyPreCombatLogic")
    public static class EnableWorkaroundPreBattle {
        @SpirePrefixPatch
        public static void patch() {
            workaround = true;
        }
    }

    @SpirePatch2(clz = GainEnergyAndEnableControlsAction.class, method = "update")
    public static class EndWorkaround {
        //Happens right after the duration check of the update so right before the energy is filled. turnHasEnded is set to false at the end of this action
        @SpireInsertPatch(rloc = 1)
        public static void patch() {
            workaround = false;
        }
    }
}

