package thePackmaster.patches.evenoddpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import thePackmaster.cardmodifiers.evenoddpack.AuraTextModifier;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {String.class, String.class, String.class, int.class, String.class, AbstractCard.CardType.class, AbstractCard.CardColor.class, AbstractCard.CardRarity.class, AbstractCard.CardTarget.class, DamageInfo.DamageType.class}
)
public class AddAuraTextPatch {
    @SpirePostfixPatch
    public static void AddAuraText(AbstractCard __instance, String id, String name, String imgUrl, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, DamageInfo.DamageType dType) {
        CardModifierManager.addModifier(__instance, new AuraTextModifier());
    }
}
