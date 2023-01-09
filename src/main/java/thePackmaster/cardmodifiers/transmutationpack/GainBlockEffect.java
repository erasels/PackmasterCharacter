package thePackmaster.cardmodifiers.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

@AbstractCardModifier.SaveIgnore
public class GainBlockEffect extends AbstractExtraEffectModifier {
    private static final String ID = SpireAnniversary5Mod.makeID("GainBlockEffect");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String[] TEXT = cardStrings.EXTENDED_DESCRIPTION;

    public GainBlockEffect(AbstractCard card, boolean isMutable, int times) {
        super(card, VariableType.BLOCK, isMutable, times);
        priority = -1;
    }

    @Override
    public void doExtraEffects(AbstractCard card, AbstractPlayer p, AbstractCreature m) {
        for (int i = 0; i < amount; ++i) {
            addToTop(new GainBlockAction(p, p, value));
        }
    }

    @Override
    public String addExtraText(String rawDescription, AbstractCard card) {
        String s = TEXT[0] + key + TEXT[1];
        s = applyTimes(s);
        s = applyMutable(s);
        return s + " NL " + rawDescription;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            ArrayList<AbstractCardModifier> list = CardModifierManager.getModifiers(card, ID);
            for (AbstractCardModifier mod : list) {
                AbstractCard c = ((AbstractExtraEffectModifier)mod).attachedCard;
                if (c.baseBlock == attachedCard.baseBlock) {
                    ((AbstractExtraEffectModifier)mod).amount++;
                    card.applyPowers();
                    card.initializeDescription();
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean shouldRenderValue() {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GainBlockEffect(attachedCard.makeStatEquivalentCopy(), isMutable, amount);
    }
}
