package thePackmaster.cardmodifiers.clawpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
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
        return CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AddClawTagAndMakeClawModifier")).TEXT[1];
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddClawTagModifier(value);
    }
}