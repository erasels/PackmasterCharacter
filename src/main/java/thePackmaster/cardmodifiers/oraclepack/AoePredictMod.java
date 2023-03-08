package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class AoePredictMod extends AbstractPredictMod {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AoePredictMod"));

    @Override
    public String identifier(AbstractCard card) {
        return SpireAnniversary5Mod.makeID("AoePredictMod");
    }

    public AoePredictMod(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.baseDamage = amount;
        ((Prophecy)card).setMultiDamage();
        ((Prophecy)card).setType(AbstractCard.CardType.ATTACK);
        ((Prophecy)card).addTarget(AbstractCard.CardTarget.ALL_ENEMY);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AoePredictMod(amount);
    }

    @Override
    public void updateAmount(AbstractCard card, int newAmount) {
        card.baseDamage = newAmount;
        amount = newAmount;
        priority = -4;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + strings.TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new DamageAllEnemiesAction(Wiz.adp(), card.multiDamage, card.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }
}
