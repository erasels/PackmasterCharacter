package thePackmaster.cardmodifiers.strikepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifierWithName;

public class AddStrikeTagModifier extends AbstractMadScienceModifierWithName {

    public AddStrikeTagModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(AbstractCard.CardTags.STRIKE)){
            card.tags.add(AbstractCard.CardTags.STRIKE);
            card.applyPowers();
        }
    }

    @Override
    public String nameSuffix(String cardName) {
        return " Strike";
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new AddStrikeTagModifier(value);
    }
}