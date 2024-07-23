package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

public class EnergyGemMod extends AbstractMadScienceModifier {

    public EnergyGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[3];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {

        Wiz.applyToSelf(new EnergizedPower(Wiz.p(), 1));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new EnergyGemMod();
    }
}