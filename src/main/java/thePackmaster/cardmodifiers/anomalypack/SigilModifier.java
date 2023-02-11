package thePackmaster.cardmodifiers.anomalypack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

@AbstractCardModifier.SaveIgnore
//SaveIgnore necessary due to the presence of AbstractCard. will crash on load without it.
public class SigilModifier extends AbstractCardModifier {

    AbstractCard toPlayCard = null;

    public SigilModifier() {
        super();
    }

    public SigilModifier(AbstractCard absorbed) {
        super();
        toPlayCard = absorbed;
    }

    //TODO: For reasons I don't know, this doesn't actually add the card preview.  Maybe happening too early in the stack, that toPlayCard is still null. Commenting out for now.
    /*
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (card.cardsToPreview == null){
            card.cardsToPreview = toPlayCard;
        }

    }

     */

    @Override
    public void onExhausted(AbstractCard card) {
        super.onExhausted(card);
        Wiz.atb(new MakeTempCardInHandAction(toPlayCard.makeStatEquivalentCopy()));
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
     
        Wiz.atb(new RepeatCardAction(toPlayCard));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SigilModifier(toPlayCard);
    }
}