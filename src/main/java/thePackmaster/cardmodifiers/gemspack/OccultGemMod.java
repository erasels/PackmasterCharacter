package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
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