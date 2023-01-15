package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.util.Wiz;

@AbstractCardModifier.SaveIgnore
//SaveIgnore necessary due to the presence of AbstractCard. will crash on load without it.
public class PlayCardModifier extends AbstractMadScienceModifier {

    AbstractCard toPlayCard = null;

    public PlayCardModifier(int valueIn) {
        super(valueIn);
    }

    public PlayCardModifier(int valueIn, AbstractCard absorbed) {
        super(valueIn);
        toPlayCard = absorbed;
    }



    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (card.cardsToPreview == null){
            card.cardsToPreview = toPlayCard.makeStatEquivalentCopy();
        }

    }

    @Override
    public void onExhausted(AbstractCard card) {
        super.onExhausted(card);
        Wiz.atb(new MakeTempCardInHandAction(toPlayCard.makeStatEquivalentCopy()));
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[3] + toPlayCard.name + ".";
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        Wiz.atb(new RepeatCardAction(toPlayCard));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PlayCardModifier(value, toPlayCard);
    }
}