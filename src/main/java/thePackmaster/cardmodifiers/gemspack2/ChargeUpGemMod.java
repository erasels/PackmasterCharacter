package thePackmaster.cardmodifiers.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

public class ChargeUpGemMod extends AbstractMadScienceModifier {

    public ChargeUpGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[10];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        Wiz.applyToSelf(new ChargeUpPower(2));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ChargeUpGemMod();
    }
}