package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CheapenModifier extends AbstractMadScienceModifier {

    public CheapenModifier(int valueIn){
        super(valueIn);
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.modifyCostForCombat(-99);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CheapenModifier(value);
    }
}