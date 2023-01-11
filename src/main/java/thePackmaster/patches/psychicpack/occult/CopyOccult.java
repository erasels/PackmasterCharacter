package thePackmaster.patches.psychicpack.occult;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = "makeStatEquivalentCopy"
)
public class CopyOccult {
    @SpirePostfixPatch
    public static AbstractCard transferIt(AbstractCard __result, AbstractCard __instance)
    {
        OccultFields.isOccult.set(__result, OccultFields.isOccult.get(__instance));
        __result.initializeDescription();
        return __result;
    }
}
