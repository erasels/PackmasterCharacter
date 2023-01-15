package thePackmaster.hats;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.patches.MainMenuUIPatch;

import java.io.IOException;


@SpirePatch(
        clz = VictoryScreen.class,
        method = "update"
)
public class VictoryScreenUnlockPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )

    public static SpireReturn Insert(VictoryScreen __instance) {
        // HAT UNLOCKS

        if (AbstractDungeon.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER)) {
            BaseMod.logger.info("Unlocking new hats!");
            for (AbstractCardPack p : SpireAnniversary5Mod.currentPoolPacks) {
                BaseMod.logger.info("Adding " + p.packID + " to unlocked hats!");
                MainMenuUIPatch.hatMenu.hats.add(p.packID);
            }
            try {
                BaseMod.logger.info("Saving unlocked hats!");
                SpireAnniversary5Mod.saveUnlockedHats(MainMenuUIPatch.hatMenu.hats);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return SpireReturn.Continue();
    }


    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ReturnToMenuButton.class, "hide");
            int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, methodCallMatcher);
            return lines;
        }
    }
}