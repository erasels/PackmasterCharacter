package thePackmaster.cardmodifiers.strikepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;

public class AddStrikeTagModifier extends AbstractMadScienceModifier {

    public AddStrikeTagModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(AbstractCard.CardTags.STRIKE)){
            card.tags.add(AbstractCard.CardTags.STRIKE);
        }
    }

    @Override
    public void alterName(AbstractCard card) {
        modifyName(card.name + " Strike", card);
        super.alterName(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddStrikeTagModifier(value);
    }
}