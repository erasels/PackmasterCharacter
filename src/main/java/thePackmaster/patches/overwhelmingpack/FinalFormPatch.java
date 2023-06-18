package thePackmaster.patches.overwhelmingpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;
import thePackmaster.powers.overwhelmingpack.ActuallyFinalFormPower;
import thePackmaster.util.Wiz;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class FinalFormPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void PlayCardForFinalForm(GameActionManager __instance) {
        if (__instance.cardQueue.get(0).card == null) {
            //current next cardqueue item is end turn cardqueue item

            AbstractPower p = AbstractDungeon.player.getPower(ActuallyFinalFormPower.POWER_ID);
            if (p instanceof ActuallyFinalFormPower && ((ActuallyFinalFormPower) p).ready && p.amount > 0) {
                ((ActuallyFinalFormPower) p).ready = false;

                AbstractCard c = getHighestCostCard();
                if (c == null)
                    return;

                p.flash();

                float x = Settings.WIDTH / 3f;
                float y = Settings.HEIGHT / 2f;

                c.target_x = x;
                c.target_y = y;

                for (int i = 1; i < p.amount; ++i) {
                    AbstractCard tmp = c.makeStatEquivalentCopy();
                    tmp.purgeOnUse = true;

                    x -= 30f * Settings.scale;
                    tmp.target_x = x;
                    tmp.target_y = y;

                    __instance.addCardQueueItem(
                            new CardQueueItem(tmp, true, EnergyPanel.getCurrentEnergy(), false, true), true);
                }
                __instance.addCardQueueItem(
                        new CardQueueItem(c, true, EnergyPanel.getCurrentEnergy(), false, true), true);
            }
        }
    }

    private static AbstractCard getHighestCostCard() {
        if (AbstractDungeon.player.hand.isEmpty())
            return null;

        AbstractCard highest = null;
        int cost = Integer.MIN_VALUE;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (highest == null || Wiz.getLogicalCardCost(c) > cost) {
                highest = c;
                cost = Wiz.getLogicalCardCost(c);
            }
        }
        return highest;
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            return LineFinder.findInOrder(ctBehavior, new Matcher.FieldAccessMatcher(GameActionManager.class, "usingCard"));
        }
    }
}
