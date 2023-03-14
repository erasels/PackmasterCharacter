package thePackmaster.patches.odditiespack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import javassist.CtBehavior;
import thePackmaster.powers.odditiespack.AutoBattlerPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AutoBattlerPatches {

    @SpirePatch(
            clz = AbstractCard.class,
            method = "isHoveredInHand"
    )
    public static class NoDoingThingsAutoBattlerPatch {
        public static SpireReturn<Boolean> Prefix(AbstractCard __instance) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID) && !AbstractDungeon.isScreenUp) {
                return SpireReturn.Return(false);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = EndTurnButton.class,
            method = "update"
    )
    public static class NoUpdateEndTurnButtonAutoBattlerPatch {
        public static SpireReturn<Boolean> Prefix(EndTurnButton __instance) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID)) {
                return SpireReturn.Return(false);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = InputHelper.class,
            method = "getCardSelectedByHotkey"
    )
    public static class NoHotkeys {
        public static SpireReturn<AbstractCard> Prefix(CardGroup cards) {
            if (AbstractDungeon.player.hasPower(AutoBattlerPower.POWER_ID) && !AbstractDungeon.isScreenUp) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = PressEndTurnButtonAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class NoClashPlz {
        public static void Postfix(PressEndTurnButtonAction __instance) {
            OnRefreshHandCheckToPlayCardPatch.isEndingTurn = true;
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
                    AbstractMonster target = Wiz.getFrontmostEnemy();
                    if (target == null) {
                        AbstractDungeon.actionManager.addToBottom(new PressEndTurnButtonAction());
                    } else {
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
                            AbstractDungeon.actionManager.addToBottom(new PressEndTurnButtonAction());
                        }
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
