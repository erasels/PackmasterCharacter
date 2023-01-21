package thePackmaster.patches.odditiespack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import thePackmaster.cards.highenergypack.StruckByATrain;
import thePackmaster.powers.odditiespack.AutoBattlerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AutoBattlerPatches {

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("AutoBattlerPatches"));

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUse"
    )
    public static class NoMoreManualPlay {
        public static boolean cardsCanBePlayed = false;

        public static boolean Postfix(boolean __result, AbstractCard c, AbstractPlayer p, AbstractMonster m) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID) && !cardsCanBePlayed) {
                return false;
            }
//            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID) && AbstractDungeon.player.hand.contains(c)) {
//                int idx = AbstractDungeon.player.hand.group.indexOf(c);
//                for (int i = 0; i < idx; i++) {
//                    AbstractCard other = AbstractDungeon.player.hand.group.get(i);
//                    if (other.canUse(p, m)) {
//                        c.cantUseMessage = uiStrings.TEXT[0];
//                        return false;
//                    }
//                }
//            }
            return __result;
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "refreshHandLayout"
    )
    public static class OnRefreshHandCheckToPlayCardPatch {
        static ReflectionHacks.RMethod playCard;
        public static boolean isEndingTurn;

        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(CardGroup __instance) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID)) {
                if (!AbstractDungeon.isScreenUp && AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.actionManager.currentAction == null && !isEndingTurn) {
                    NoMoreManualPlay.cardsCanBePlayed = true;
                    AbstractMonster target = StruckByATrain.getFrontmostEnemy();
                    boolean foundACard = false;
                    for (AbstractCard q : AbstractDungeon.player.hand.group) {
                        if (q.canUse(AbstractDungeon.player, target)) {
                            foundACard = true;
                            AbstractDungeon.player.hoveredCard = q;
                            ReflectionHacks.setPrivate(AbstractDungeon.player, AbstractPlayer.class, "hoveredMonster", target);
                            if (playCard == null)
                                playCard = ReflectionHacks.privateMethod(AbstractPlayer.class, "playCard");
                            playCard.invoke(AbstractDungeon.player);
                            break;
                        }
                    }
                    if (!foundACard) {
                        isEndingTurn = true;
                        AbstractDungeon.actionManager.callEndTurnEarlySequence();
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }


}
