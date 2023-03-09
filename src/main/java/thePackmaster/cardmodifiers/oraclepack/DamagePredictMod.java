package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.util.Wiz;

public class DamagePredictMod extends AbstractPredictMod {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DamagePredictMod"));

    @Override
    public String identifier(AbstractCard card) {
        return SpireAnniversary5Mod.makeID("DamagePredictMod");
    }

    public DamagePredictMod(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        ((Prophecy)card).baseSecondDamage = amount;
        ((Prophecy)card).setType(AbstractCard.CardType.ATTACK);
        ((Prophecy)card).addTarget(AbstractCard.CardTarget.ENEMY);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamagePredictMod(amount);
    }

    @Override
    public void updateAmount(AbstractCard card, int newAmount) {
        ((Prophecy)card).baseSecondDamage = newAmount;
        amount = newAmount;
        priority = -5;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return strings.TEXT[0] + " NL " + rawDescription;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new DamageAction(target, new DamageInfo(AbstractDungeon.player,((Prophecy)card).secondDamage), AbstractGameAction.AttackEffect.LIGHTNING));
    }
}
