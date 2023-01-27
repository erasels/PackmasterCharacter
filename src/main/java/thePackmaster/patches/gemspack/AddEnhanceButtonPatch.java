package thePackmaster.patches.gemspack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import javassist.CtBehavior;
import thePackmaster.packs.GemsPack;
import thePackmaster.ui.EnhanceBonfireOption;
import thePackmaster.vfx.gemspack.SocketGemEffect;

import java.util.ArrayList;

public class AddEnhanceButtonPatch {
    @SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
    public static class AddKeys {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {
            boolean active = true;

            //If there are no gems, don't show the button.
            if (SocketGemEffect.getGems().isEmpty()) {
                return;
            }

            //if any of the reasons you can't socket are true, the button should be disabled.
            if (SocketGemEffect.getGems().isEmpty() ||
                    SocketGemEffect.getModifiableCards().isEmpty() ||
                    AbstractDungeon.player.gold < GemsPack.goldCostToSocket) {
                active = false;
            }
                GemsPack.socketBonfireOption = new EnhanceBonfireOption(active);
                ___buttons.add(GemsPack.socketBonfireOption);

        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
