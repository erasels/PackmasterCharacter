package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cards.gemspack.Ward;
import thePackmaster.util.Wiz;

public class WardGemMod extends AbstractMadScienceModifier {

    public WardGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[8];
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.cardsToPreview = new Ward();
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {

        Wiz.atb(new MakeTempCardInHandAction(new Ward()));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new WardGemMod();
    }
}