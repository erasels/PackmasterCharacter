package thePackmaster.patches.compatibility;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@SpirePatch(cls = "infinitespire.patches.AbstractDungeonPatch$InitFirstRoomPatch", method = "initQuestLog", optional = true)
public class InfiniteSpirePatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> preventQuestGeneration(AbstractDungeon __instance, String name, String levelId, AbstractPlayer p, ArrayList<String> newSpecialOneTimeEventList) {
        // We need to prevent Infinite Spire from generating quests at the very start of the run because the Packmaster's
        // card pool hasn't been defined yet (and Infinite Spire uses the card pool to make "pick up card X" quests)
        // Instead, we invoke Infinite Spire's quest generation ourselves by calling generateQuestsIfInfiniteSpireIsLoaded
        // after the Packmaster's card pool has been defined
        if(p != null && p.chosenClass == ThePackmaster.Enums.THE_PACKMASTER && AbstractDungeon.floorNum == 0 && !CardCrawlGame.loadingSave) {
            clearQuests();
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    @SuppressWarnings("rawtypes")
    public static void clearQuests() {
        if (Loader.isModLoaded("infinitespire")) {
            SpireAnniversary5Mod.logger.info("Infinite Spire compatibility: clearing quest log while pack selection is happening");
            try {
                Field questLogField = Class.forName("infinitespire.InfiniteSpire").getField("questLog");
                ArrayList questLog = (ArrayList)questLogField.get(null);
                questLog.clear();
            } catch (IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
                // If something is wrong with Infinite Spire, so be it. Just keep going.
                e.printStackTrace();
            }
        }
    }

    public static void generateQuestsIfInfiniteSpireIsLoaded() {
        if (Loader.isModLoaded("infinitespire")) {
            SpireAnniversary5Mod.logger.info("Infinite Spire compatibility: adding initial quests now that pack selection is finished");
            try {
                Method addInitialQuestsMethod = Class.forName("infinitespire.InfiniteSpire").getMethod("addInitialQuests");
                addInitialQuestsMethod.invoke(null);
            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                // If something is wrong with Infinite Spire, so be it. Just keep going.
                e.printStackTrace();
            }
        }
    }
}
