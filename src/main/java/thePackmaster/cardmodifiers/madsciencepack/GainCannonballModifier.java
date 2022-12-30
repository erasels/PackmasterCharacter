package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.legacypack.Cannonball;

public class GainCannonballModifier extends AbstractMadScienceModifier {

    public GainCannonballModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (value > 0) {
            return rawDescription + CardCrawlGame.languagePack.getUIString("MadScienceModifiers").TEXT[7] + "+";
        } else {
            return rawDescription + CardCrawlGame.languagePack.getUIString("MadScienceModifiers").TEXT[7];
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        AbstractCard c = new Cannonball();
        if (value>0) c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (card.cardsToPreview != null) {
            AbstractCard c = new Cannonball();
            if (value>0) c.upgrade();
            card.cardsToPreview = c;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GainCannonballModifier(value);
    }
}