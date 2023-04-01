package thePackmaster.cardmodifiers.madsciencepack;

import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractMadScienceModifierWithName extends AbstractMadScienceModifier {

    public AbstractMadScienceModifierWithName(int valueIn){
        this.value = valueIn;
    }

    public AbstractMadScienceModifierWithName(){
        this.value = 0;
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return cardName + nameSuffix(cardName) + "^";
    }

    public abstract String nameSuffix(String cardName);
}