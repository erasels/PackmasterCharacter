package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

public abstract class AbstractMadScienceModifier extends AbstractCardModifier {
    public int value;

    public AbstractMadScienceModifier(int valueIn){
        this.value = valueIn;
    }

    public AbstractMadScienceModifier(){
        this.value = 0;
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return cardName + "^";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(SpireAnniversary5Mod.ISCARDMODIFIED);
    }
}