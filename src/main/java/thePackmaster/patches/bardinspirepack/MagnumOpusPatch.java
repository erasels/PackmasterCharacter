package thePackmaster.patches.bardinspirepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.cards.bardinspirepack.MagnumOpus;

@SpirePatch2(clz = CardLibrary.class, method = "getCopy", paramtypez = { String.class, int.class, int.class })
public class MagnumOpusPatch {
    @SpirePostfixPatch
    public static void fixDescription(AbstractCard __result, String key, int upgradeTime, int misc) {
        if (__result instanceof MagnumOpus) {
            MagnumOpus c = (MagnumOpus)__result;
            c.secondMagic = c.baseSecondMagic = c.misc;
            c.initializeDescription();
        }
    }
}
