package thePackmaster.patches.gemspack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import javassist.CtBehavior;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.GemsPack;
import thePackmaster.ui.EnhanceBonfireOption;
import thePackmaster.vfx.gemspack.SocketGemEffect;

import java.util.ArrayList;

public class AddEnhanceButtonPatch {
    @SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
    public static class AddKeys {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {
            Boolean active = true;
            if (SocketGemEffect.getModifiableCards().isEmpty()) {
                active = false;
            }
            if (SocketGemEffect.getGems().isEmpty()) {
                active = false;
            }
            if (AbstractDungeon.player.gold < GemsPack.goldCostToSocket) {
                active = false;
            }
            if (active) {
                GemsPack.socketBonfireOption = new EnhanceBonfireOption(active);
                ___buttons.add(GemsPack.socketBonfireOption);
            }

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
