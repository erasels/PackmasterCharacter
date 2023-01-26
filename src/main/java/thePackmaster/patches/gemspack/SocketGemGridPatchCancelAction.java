package thePackmaster.patches.gemspack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;
import javassist.CtBehavior;
import thePackmaster.packs.GemsPack;
import thePackmaster.vfx.gemspack.SocketGemEffect;

@SpirePatch(clz = CancelButton.class, method = "update")
public class SocketGemGridPatchCancelAction {

    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(CancelButton obj) {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && GemsPack.gridScreenForSockets) {
            if (!AbstractDungeon.gridSelectScreen.confirmScreenUp) {
                CardGroup gemCards = SocketGemEffect.getGems();
                if (GemsPack.currSocketGemEffect != null) {
                    GemsPack.currSocketGemEffect.gemSelect = true;
                    GemsPack.currSocketGemEffect.socketSelect = false;
                    GemsPack.currSocketGemEffect.gemChosen = null;
                }
                GemsPack.gridScreenForSockets = false;
                GemsPack.gridScreenForGems = true;
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.gridSelectScreen.open(gemCards, 1, SocketGemEffect.TEXT[3], false, false, true, false);
            }
        }

        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && GemsPack.gridScreenForGems) {
            if (!AbstractDungeon.gridSelectScreen.confirmScreenUp) {
                GemsPack.gridScreenForGems = false;
                GemsPack.gridScreenForSockets = false;
                AbstractDungeon.closeCurrentScreen();
                if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
                    RestRoom r = (RestRoom) AbstractDungeon.getCurrRoom();
                    r.campfireUI.reopen();
                }
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "screenSwap");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}