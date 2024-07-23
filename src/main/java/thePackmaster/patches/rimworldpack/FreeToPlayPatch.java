package thePackmaster.patches.rimworldpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cards.rimworldpack.FreeToPlayPower;
import thePackmaster.util.Wiz;

public class FreeToPlayPatch {
    @SpirePatch2(clz = AbstractCard.class, method = "freeToPlay")
    public static class FreeCardPlz {
        @SpirePrefixPatch
        public static SpireReturn<?> free(AbstractCard __instance) {
            if (Wiz.isInCombat() && !AbstractDungeon.isScreenUp) {
                for (AbstractPower pow : Wiz.adp().powers) {
                    if (pow instanceof FreeToPlayPower && ((FreeToPlayPower) pow).isFreeToPlay(__instance)) {
                        return SpireReturn.Return(true);
                    }
                }
                if(__instance.color == AbstractCard.CardColor.CURSE && Wiz.adp().hasRelic(BlueCandle.ID) && EnergyPanel.getCurrentEnergy() < __instance.cost)
                    return SpireReturn.Return(true);
            }
            return SpireReturn.Continue();
        }
    }
}