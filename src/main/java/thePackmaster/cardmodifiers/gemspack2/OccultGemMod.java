package thePackmaster.cardmodifiers.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.patches.psychicpack.occult.OccultFields;

public class OccultGemMod extends AbstractMadScienceModifier {

    public OccultGemMod() {
        super();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        OccultFields.isOccult.set(card, true);
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new OccultGemMod();
    }
}