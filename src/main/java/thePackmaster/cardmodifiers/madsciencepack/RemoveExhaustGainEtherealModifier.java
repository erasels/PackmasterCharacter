package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;

public class RemoveExhaustGainEtherealModifier extends AbstractMadScienceModifier {

    public RemoveExhaustGainEtherealModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return (CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[6] + rawDescription).replace(
                "Exhaust.",""
        );
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.isEthereal = true;
        card.exhaust = false;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RemoveExhaustGainEtherealModifier(value);
    }
}