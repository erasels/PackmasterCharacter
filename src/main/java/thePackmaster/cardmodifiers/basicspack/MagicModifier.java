package thePackmaster.cardmodifiers.basicspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;

public class MagicModifier extends AbstractCardModifier {
    private final int value;
    private final boolean secondMagic;

    public MagicModifier(int value){
        this.value = value;
        this.secondMagic = false;
    }

    public MagicModifier(int value, boolean secondMagic){
        this.value = value;
        this.secondMagic = secondMagic;
    }

    public boolean shouldApply(AbstractCard card) {
        if(secondMagic && card instanceof AbstractPackmasterCard){
            return ((AbstractPackmasterCard) card).secondMagic != -1;
        } else return card.magicNumber != -1;
    }

    public void onInitialApplication(AbstractCard card) {
        if(secondMagic && card instanceof AbstractPackmasterCard){
            ((AbstractPackmasterCard) card).secondMagic += value;
            ((AbstractPackmasterCard) card).baseSecondMagic += value;
        } else {
            card.magicNumber += value;
            card.baseMagicNumber += value;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MagicModifier(value, secondMagic);
    }
}