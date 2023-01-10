package thePackmaster.patches.psychicpack.occult;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@SpirePatch(
        clz = AbstractCard.class,
        method = "initializeDescription"
)
public class OccultDescription {
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Occult"));
    public static String occult = uiStrings.TEXT[0];

    @SpirePrefixPatch
    public static void betterHaveTheKeyword(AbstractCard __instance)
    {
        if (OccultFields.isOccult.get(__instance) && !__instance.rawDescription.contains(occult))
        {
            __instance.rawDescription = occult + __instance.rawDescription;
        }
    }
}
