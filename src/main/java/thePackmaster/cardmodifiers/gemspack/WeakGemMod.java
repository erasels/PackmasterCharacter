package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

public class WeakGemMod extends AbstractMadScienceModifier {

    public WeakGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[9];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if (card.target == AbstractCard.CardTarget.ENEMY) {
            Wiz.applyToEnemy((AbstractMonster) target, new WeakPower(target, 1, false));
        } else {
            AbstractMonster m = Wiz.getRandomEnemy();
            Wiz.applyToEnemy(m, new WeakPower(m, 1, false));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new WeakGemMod();
    }
}