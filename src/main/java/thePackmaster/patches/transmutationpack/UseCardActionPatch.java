package thePackmaster.patches.transmutationpack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class UseCardActionPatch {
    @SpirePatch(
            clz = UseCardAction.class,
            method = SpirePatch.CLASS
    )
    public static class UseCardActionField {
        public static SpireField<AbstractCard> transmuteTargetCard = new SpireField<>(() -> null);
    }

    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionInsertPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(UseCardAction __instance) {
            //if the played card was transmuted, handle separately from regular UseCardAction logic
            AbstractCard newCard = UseCardActionField.transmuteTargetCard.get(__instance);
            if (newCard != null) {
                AbstractCard card = ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");
                AbstractDungeon.player.limbo.removeCard(card);
                AbstractDungeon.player.limbo.removeCard(newCard);
                ReflectionHacks.setPrivate(__instance, UseCardAction.class, "targetCard", newCard);
                __instance.isDone = true;
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(UseCardAction.class, "exhaustCard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
