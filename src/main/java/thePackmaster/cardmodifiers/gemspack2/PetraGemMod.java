package thePackmaster.cardmodifiers.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.util.Wiz;

public class PetraGemMod extends AbstractMadScienceModifier {

    public PetraGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[17];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if (card.target == AbstractCard.CardTarget.ENEMY) {
            Wiz.applyToEnemy((AbstractMonster) target, new PetraPower(target, 1));
        } else {
            AbstractMonster m = Wiz.getRandomEnemy();
            Wiz.applyToEnemy(m, new PetraPower(m, 1));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PetraGemMod();
    }
}