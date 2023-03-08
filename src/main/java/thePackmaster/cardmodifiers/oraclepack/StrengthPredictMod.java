package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.util.Wiz;

public class StrengthPredictMod extends AbstractPredictMod {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("StrengthPredictMod"));

    @Override
    public String identifier(AbstractCard card) {
        return SpireAnniversary5Mod.makeID("StrengthPredictMod");
    }

    public StrengthPredictMod(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        ((Prophecy)card).baseSecondMagic = ((Prophecy)card).secondMagic = amount;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StrengthPredictMod(amount);
    }

    @Override
    public void updateAmount(AbstractCard card, int newAmount) {
        ((Prophecy)card).baseSecondMagic = ((Prophecy)card).secondMagic = newAmount;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + strings.TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new ApplyPowerAction(Wiz.adp(),Wiz.adp(), new StrengthPower(Wiz.adp(), ((Prophecy)card).secondMagic)));
    }
}
