package thePackmaster.cardmodifiers.clawpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifierWithName;

public class AddClawTagModifier extends AbstractMadScienceModifierWithName {

    public AddClawTagModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(SpireAnniversary5Mod.CLAW)){
            card.tags.add(SpireAnniversary5Mod.CLAW);
            card.applyPowers();
        }
    }

    @Override
    public String nameSuffix(String cardName) {
        return " Claw";
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddClawTagModifier(value);
    }
}