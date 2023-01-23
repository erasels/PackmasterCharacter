package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;

public class PersistGemMod extends AbstractMadScienceModifier {

    public PersistGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[16];
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        PersistFields.basePersist.set(card, 2);
        PersistFields.persist.set(card, 2);
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new PersistGemMod();
    }
}