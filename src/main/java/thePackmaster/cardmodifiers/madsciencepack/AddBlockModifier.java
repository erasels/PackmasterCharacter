package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AddBlockModifier extends AbstractMadScienceModifier {

    public AddBlockModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.baseBlock += value;
        card.update();
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddBlockModifier(value);
    }
}