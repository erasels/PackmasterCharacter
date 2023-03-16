package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import javassist.CtBehavior;
import thePackmaster.relics.PMBoosterBox;
import thePackmaster.rewards.PMBoosterBoxCardReward;
import thePackmaster.rewards.SingleCardReward;

public class PMBoosterBoxPatch {
    @SpirePatch(clz = AbstractRoom.class, method = "update")
    public static class AddRewardPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void addRewards(AbstractRoom __instance) {
            if (AbstractDungeon.player.hasRelic(PMBoosterBox.ID)) {
                AbstractDungeon.getCurrRoom().rewards.add(new PMBoosterBoxCardReward());
                PMBoosterBox.incrementRewards();
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractRoom.class, "addPotionToRewards");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class PopulateCardsPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void populateCards(CombatRewardScreen __instance) {
            for (int i = 0; i < __instance.rewards.size(); i++) {
                RewardItem rewardItem = __instance.rewards.get(i);
                if (rewardItem instanceof PMBoosterBoxCardReward) {
                    PMBoosterBoxCardReward reward = (PMBoosterBoxCardReward)rewardItem;
                    reward.populateCards();
                    //One of the ugliest hacks I've ever written
                    if(reward.getCards().size() == 1) {
                        __instance.rewards.set(i, new SingleCardReward(reward.getCards().get(0)));
                    }
                }
            }
            AbstractDungeon.combatRewardScreen.positionRewards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ProceedButton.class, "show");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
