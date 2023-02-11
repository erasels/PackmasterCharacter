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
            for (RewardItem rewardItem : __instance.rewards) {
                if (rewardItem instanceof PMBoosterBoxCardReward) {
                    PMBoosterBoxCardReward reward = (PMBoosterBoxCardReward)rewardItem;
                    reward.populateCards();
                }
            }
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
