package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class FloatAndRetainModifier extends AbstractMadScienceModifier {

    public FloatAndRetainModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CardCrawlGame.languagePack.getUIString("MadScienceModifiers").TEXT[2] + rawDescription + CardCrawlGame.languagePack.getUIString("MadScienceModifiers").TEXT[3];
    }

    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.selfRetain = true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        //TODO - Float
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FloatAndRetainModifier(value);
    }
}