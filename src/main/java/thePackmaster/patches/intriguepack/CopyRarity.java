package thePackmaster.patches.intriguepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.patches.psychicpack.occult.OccultFields;

@SpirePatch(
        clz = AbstractCard.class,
        method = "makeStatEquivalentCopy"
)
public class CopyRarity {
    @SpirePostfixPatch
    public static AbstractCard transferIt(AbstractCard __result, AbstractCard __instance)
    {
        __result.rarity = __instance.rarity;
        return __result;
    }
}
