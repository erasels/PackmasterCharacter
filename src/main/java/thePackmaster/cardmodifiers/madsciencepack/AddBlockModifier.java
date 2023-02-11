package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AddBlockModifier extends AbstractMadScienceModifier {

    public AddBlockModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        return block + value;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddBlockModifier(value);
    }
}