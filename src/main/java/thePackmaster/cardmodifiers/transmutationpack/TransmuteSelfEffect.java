package thePackmaster.cardmodifiers.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;

@AbstractCardModifier.SaveIgnore
public class TransmuteSelfEffect extends AbstractExtraEffectModifier {
    private static final String ID = SpireAnniversary5Mod.makeID("TransmuteSelfEffect");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String[] TEXT = cardStrings.EXTENDED_DESCRIPTION;

    public TransmuteSelfEffect(AbstractCard card, boolean isMutable) {
        super(card, VariableType.MAGIC, isMutable, 1);
        priority = 0;
    }

    @Override
    public void doExtraEffects(AbstractCard card, AbstractPlayer p, AbstractCreature m) {
        addToBot(new TransmuteCardAction(card));
    }

    @Override
    public String addExtraText(String rawDescription, AbstractCard card) {
        String s = TEXT[0];
        s = applyMutable(s);
        return rawDescription + " NL " + s;
    }

    @Override
    public boolean shouldRenderValue() {
        return false;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TransmuteSelfEffect(attachedCard, isMutable);
    }
}
