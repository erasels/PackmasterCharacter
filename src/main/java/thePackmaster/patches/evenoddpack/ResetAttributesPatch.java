package thePackmaster.patches.evenoddpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import thePackmaster.cardmodifiers.evenoddpack.AuraTextModifier;

@SpirePatch(
        clz = AbstractCard.class,
        method = "resetAttributes"
)
public class ResetAttributesPatch {
    @SpirePostfixPatch
    public static void RemoveAuraText(AbstractCard __instance) {
        if(CardModifierManager.hasModifier(__instance, AuraTextModifier.MODIFIER_ID)
            && ((AuraTextModifier) (CardModifierManager.getModifiers(__instance, AuraTextModifier.MODIFIER_ID).get(0)))
                .hasAuraText)
        {
            CardModifierManager.removeModifiersById(__instance, AuraTextModifier.MODIFIER_ID, true);
            __instance.initializeDescription();
        }
    }
}
