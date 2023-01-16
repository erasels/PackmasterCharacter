package thePackmaster.patches.odditiespack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.odditiespack.AutoBattlerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AutoBattlerPatches {

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("AutoBattlerPatches"));

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUse"
    )
    public static class NoMoreManualPlay {
        //public static boolean cardsCanBePlayed = false;

        public static boolean Postfix(boolean __result, AbstractCard c, AbstractPlayer p, AbstractMonster m) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID) && AbstractDungeon.player.hand.contains(c)) {
                int idx = AbstractDungeon.player.hand.group.indexOf(c);
                for (int i = 0; i < idx; i++) {
                    AbstractCard other = AbstractDungeon.player.hand.group.get(i);
                    if (other.canUse(p, m)) {
                        c.cantUseMessage = uiStrings.TEXT[0];
                        return false;
                    }
                }
            }
            return __result;
        }
    }


}
