package thePackmaster.hats;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;

import java.io.IOException;
import java.util.ArrayList;


@SpirePatch(
        clz = VictoryScreen.class,
        method = "update"
)
public class VictoryScreenUnlockPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )

    public static void Insert(VictoryScreen __instance) {
        // HAT UNLOCKS

        if (SpireAnniversary5Mod.allPacksMode) SpireAnniversary5Mod.logger.info("All packs mode - no Hat unlocks!");
        if (AbstractDungeon.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER) && !SpireAnniversary5Mod.allPacksMode) {
            SpireAnniversary5Mod.logger.info("Unlocking new hats!");
            ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();
            ArrayList<String> unlockedRainbows = SpireAnniversary5Mod.getUnlockedRainbows();
            for (AbstractCardPack p : SpireAnniversary5Mod.currentPoolPacks) {
                SpireAnniversary5Mod.logger.info("Adding " + p.packID + " to unlocked hats!");
                if (!unlockedHats.contains(p.packID)) {
                    unlockedHats.add(p.packID);
                }
                if (AbstractDungeon.ascensionLevel >= 20 && AbstractDungeon.actNum>=4) {
                    SpireAnniversary5Mod.logger.info("Adding " + p.packID + "to unlocked rainbows!");
                    if (!unlockedRainbows.contains(p.packID)) {
                        unlockedRainbows.add(p.packID);
                    }
                }
            }
            try {
                SpireAnniversary5Mod.logger.info("Saving unlocked hats!");
                SpireAnniversary5Mod.saveUnlockedHats(unlockedHats);
                SpireAnniversary5Mod.saveUnlockedRainbows(unlockedRainbows);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            HatMenu.refreshHatDropdown();
        }
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