package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;

public class RetainGemMod extends AbstractMadScienceModifier {

    public RetainGemMod() {
        super();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.selfRetain = true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[3] + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RetainGemMod();
    }
}