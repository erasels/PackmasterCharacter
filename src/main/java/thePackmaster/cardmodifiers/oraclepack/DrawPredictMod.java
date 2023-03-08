package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.util.Wiz;

public class DrawPredictMod extends AbstractPredictMod {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DrawPredictMod"));

    @Override
    public String identifier(AbstractCard card) {
        return SpireAnniversary5Mod.makeID("DrawPredictMod");
    }

    public DrawPredictMod(int amount) {
        this.amount = amount;
        priority = -1;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.baseMagicNumber = card.magicNumber = amount;
        ((Prophecy)card).addTarget(AbstractCard.CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DrawPredictMod(amount);
    }

    @Override
    public void updateAmount(AbstractCard card, int newAmount) {
        card.baseMagicNumber = card.magicNumber = newAmount;
        amount = newAmount;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + strings.TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new DrawCardAction(card.magicNumber));
    }
}
