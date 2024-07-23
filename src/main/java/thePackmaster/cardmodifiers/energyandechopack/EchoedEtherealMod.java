package thePackmaster.cardmodifiers.energyandechopack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.SpireAnniversary5Mod;

//Basically Ethereal Modifier but it will go at the bottom and won't NL
public class EchoedEtherealMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("EchoedEtherealMod");

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " " + StringUtils.capitalize(GameDictionary.ETHEREAL.NAMES[0]) + (Settings.lineBreakViaCharacter ? " " : "") + LocalizedStrings.PERIOD;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.isEthereal = true;
    }

    public void onRemove(AbstractCard card) {
        card.isEthereal = false;
    }

    public AbstractCardModifier makeCopy() {
        return new EchoedEtherealMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
