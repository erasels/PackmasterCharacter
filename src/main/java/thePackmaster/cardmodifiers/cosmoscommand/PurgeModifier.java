package thePackmaster.cardmodifiers.cosmoscommand;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PurgeModifier extends AbstractCardModifier {
    public static String ID = makeID("PurgeCardModifier");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PurgeModifier"));
    public static final String[] TEXT = uiStrings.TEXT;

    public PurgeModifier() {}

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[0];
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.purgeOnUse;
    }

    public void onInitialApplication(AbstractCard card) {
        card.purgeOnUse = true;
    }

    public void onRemove(AbstractCard card) {
        card.purgeOnUse = false;
    }

    public AbstractCardModifier makeCopy() {
        return new PurgeModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
