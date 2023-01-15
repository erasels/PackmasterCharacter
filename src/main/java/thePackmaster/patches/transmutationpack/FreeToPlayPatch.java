package thePackmaster.patches.transmutationpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.transmutationpack.FreeToPlayEffect;

@SpirePatch2(
        clz = AbstractCard.class,
        method = "freeToPlay"
)
public class FreeToPlayPatch {
    @SpirePrefixPatch()
    public static SpireReturn<Boolean> hasFreeEffect(AbstractCard __instance) {
        if (CardModifierManager.hasModifier(__instance, FreeToPlayEffect.ID)) {
            return SpireReturn.Return(true);
        }
        return SpireReturn.Continue();
    }
}
