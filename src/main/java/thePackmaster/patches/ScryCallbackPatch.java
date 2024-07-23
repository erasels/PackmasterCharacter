package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ScryCallbackPatch {
    public static ScryAction scryWithCallback(int amt, Consumer<ArrayList<AbstractCard>> callback) {
        ScryAction action = new ScryAction(amt);
        Field.scryCallback.set(action, callback);
        return action;
    }

    @SpirePatch(
            clz = ScryAction.class,
            method = SpirePatch.CLASS
    )
    public static class Field {
        public static SpireField<Consumer<ArrayList<AbstractCard>>> scryCallback = new SpireField<>(() -> null);
    }

    @SpirePatch(
            clz = ScryAction.class,
            method = "update"
    )
    public static class DoTheCallback {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void ThisIsAnInsert(ScryAction __instance) {
            Consumer<ArrayList<AbstractCard>> callback = Field.scryCallback.get(__instance);
            if (callback != null) {
                callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
            }
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher matcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
                return LineFinder.findInOrder(ctMethodToPatch, matcher);
            }
        }
    }
}
