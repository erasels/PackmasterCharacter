package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
        import com.megacrit.cardcrawl.cards.AbstractCard;

public class AddDamageModifier extends AbstractMadScienceModifier {

    public AddDamageModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.baseDamage += value;
        card.update();
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddDamageModifier(value);
    }
}