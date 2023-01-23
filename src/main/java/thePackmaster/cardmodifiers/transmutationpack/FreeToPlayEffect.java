package thePackmaster.cardmodifiers.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;

@AbstractCardModifier.SaveIgnore
public class FreeToPlayEffect extends AbstractExtraEffectModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("FreeToPlayEffect");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String[] TEXT = cardStrings.EXTENDED_DESCRIPTION;

    public FreeToPlayEffect(boolean isMutable) {
        super(new Dazed(), VariableType.DAMAGE, isMutable, 1);
        priority = -1;
    }

    @Override
    public void doExtraEffects(AbstractCard card, AbstractPlayer p, AbstractCreature m) {
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
    }

    @Override
    public String addExtraText(String rawDescription, AbstractCard card) {
        String s = TEXT[0];
        s = applyMutable(s);
        return s + " NL " + rawDescription;
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
        return new FreeToPlayEffect(isMutable);
    }
}
