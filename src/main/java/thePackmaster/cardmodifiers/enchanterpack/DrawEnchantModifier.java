package thePackmaster.cardmodifiers.enchanterpack;

import basemod.abstracts.AbstractCardModifier;
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
        Wiz.atb(new DrawCardAction(amount));
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + strings.TEXT[0] + amount + strings.TEXT[1]; //only for 1 card at a time for now
    }
}
