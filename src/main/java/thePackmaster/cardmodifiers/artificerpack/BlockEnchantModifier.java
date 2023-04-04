package thePackmaster.cardmodifiers.artificerpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class BlockEnchantModifier extends AbstractCardModifier {

    public static final String ID = SpireAnniversary5Mod.makeID("BlockEnchantModifier");
    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(ID);

    public int amount;

    public BlockEnchantModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        for (AbstractCardModifier m : CardModifierManager.modifiers(card)) {
            if (m instanceof BlockEnchantModifier && m != this) {
                ((BlockEnchantModifier) m).amount += amount;
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
        return new BlockEnchantModifier(amount);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (amount > 0) {
            Wiz.atb(new GainBlockAction(Wiz.adp(), amount));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + strings.TEXT[0] + amount + strings.TEXT[1];
    }
}
