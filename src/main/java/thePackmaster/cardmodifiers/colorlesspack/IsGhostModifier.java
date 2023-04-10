package thePackmaster.cardmodifiers.colorlesspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AbstractCardModifier.SaveIgnore
public class IsGhostModifier extends AbstractCardModifier {
    public static final String ID = makeID("IsGhostModifier");
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public AbstractCard ghost;

    public IsGhostModifier(AbstractCard source) {
        this.ghost = source;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new IsGhostModifier(ghost.makeStatEquivalentCopy()); // Might need to do stat equiv copy of the ghost too
    }
}
