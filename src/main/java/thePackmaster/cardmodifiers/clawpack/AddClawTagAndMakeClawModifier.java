package thePackmaster.cardmodifiers.clawpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifierWithName;
import thePackmaster.cards.clawpack.GhostClaw;
import thePackmaster.util.Wiz;

public class AddClawTagAndMakeClawModifier extends AbstractMadScienceModifierWithName {

    public AddClawTagAndMakeClawModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(SpireAnniversary5Mod.CLAW)){
            card.tags.add(SpireAnniversary5Mod.CLAW);
            card.applyPowers();
        }
        if (card.cardsToPreview == null){
            card.cardsToPreview = new GhostClaw();
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AddClawTagAndMakeClawModifier")).TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
     
        AbstractCard c = new GhostClaw();
        Wiz.atb(new MakeTempCardInHandAction(c));
    }

    @Override
    public String nameSuffix(String cardName) {
        return CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AddClawTagAndMakeClawModifier")).TEXT[1];
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddClawTagAndMakeClawModifier(value);
    }
}