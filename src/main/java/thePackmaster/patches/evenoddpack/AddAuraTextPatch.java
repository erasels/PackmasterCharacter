package thePackmaster.patches.evenoddpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.evenoddpack.AuraTextModifier;
import thePackmaster.powers.evenoddpack.GammaWardPower;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;

@SpirePatch(
        clz = CardGroup.class,
        method = "refreshHandLayout"
)
public class AddAuraTextPatch {
    @SpirePostfixPatch
    public static void AddAuraText(CardGroup __instance) {
        if(AbstractDungeon.player.hasPower(GammaWardPower.POWER_ID) || AbstractDungeon.player.hasPower(PrimeDirectivePower.POWER_ID))
            for (AbstractCard card:AbstractDungeon.player.hand.group) {
                CardModifierManager.addModifier(card, new AuraTextModifier());
            }
    }
}
