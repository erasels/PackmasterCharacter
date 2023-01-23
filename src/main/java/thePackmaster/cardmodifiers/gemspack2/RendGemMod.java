package thePackmaster.cardmodifiers.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.powers.ringofpainpack.RendPower;
import thePackmaster.util.Wiz;

public class RendGemMod extends AbstractMadScienceModifier {

    public RendGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[18];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if (card.target == AbstractCard.CardTarget.ENEMY) {
            Wiz.applyToEnemy((AbstractMonster) target, new RendPower(target, 2));
        } else {
            AbstractMonster m = Wiz.getRandomEnemy();
            Wiz.applyToEnemy(m, new RendPower(m, 3));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RendGemMod();
    }
}