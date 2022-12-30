package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CLASS
)
public class CardParentPackPatch {
    public static SpireField<AbstractCardPack> parentPack = new SpireField<>(() -> null);

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            parentPack.set(__result, parentPack.get(__instance));
            return __result;
        }
    }
}