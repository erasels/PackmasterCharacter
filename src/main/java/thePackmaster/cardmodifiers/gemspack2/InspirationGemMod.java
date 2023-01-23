package thePackmaster.cardmodifiers.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.powers.bardinspirepack.InspirationPower;
import thePackmaster.util.Wiz;

public class InspirationGemMod extends AbstractMadScienceModifier {

    public InspirationGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[14];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.applyToSelf(new InspirationPower(Wiz.p(), 1, 50));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new InspirationGemMod();
    }
}