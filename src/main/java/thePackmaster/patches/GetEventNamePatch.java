package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.EventHelper;
import thePackmaster.SpireAnniversary5Mod;

@SpirePatch(
        clz = EventHelper.class,
        method = "getEventName",
        paramtypez = String.class
)
public class GetEventNamePatch {
    @SpirePrefixPatch
    public static SpireReturn<String> GetEventName(String eventID) {
        if (eventID != null && eventID.startsWith(SpireAnniversary5Mod.modID + ":")) {
            return SpireReturn.Return(CardCrawlGame.languagePack.getEventString(eventID).NAME);
        }
        return SpireReturn.Continue();
    }
}