package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

public abstract class AbstractMadScienceModifierWithName extends AbstractMadScienceModifier {

    public AbstractMadScienceModifierWithName(int valueIn){
        this.value = valueIn;
    }

    public AbstractMadScienceModifierWithName(){
        this.value = 0;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        modifyName(card.name, card);
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return cardName + nameSuffix(cardName) + "^";
    }

    public abstract String nameSuffix(String cardName);
}