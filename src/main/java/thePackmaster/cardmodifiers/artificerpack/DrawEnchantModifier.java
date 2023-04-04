package thePackmaster.cardmodifiers.artificerpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class DrawEnchantModifier extends AbstractCardModifier {

    public static final String ID = SpireAnniversary5Mod.makeID("DrawEnchantModifier");
    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(ID);

    public int amount;

    public DrawEnchantModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof DrawEnchantModifier && m != this) {
                ((DrawEnchantModifier) m).amount += amount;
                amount = 0;
            }
        }
        if (amount == 0) {
            AbstractCardModifier modifier = this;
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    CardModifierManager.removeSpecificModifier(card, modifier, true);
                    isDone = true;
                }
            });
        }
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DrawEnchantModifier(amount);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (amount>0) {
            Wiz.atb(new DrawCardAction(amount));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (amount > 1) {
            return rawDescription + strings.TEXT[0] + amount + strings.TEXT[2];
        }
        return rawDescription + strings.TEXT[0] + amount + strings.TEXT[1];
    }
}
