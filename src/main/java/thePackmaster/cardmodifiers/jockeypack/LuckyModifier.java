package thePackmaster.cardmodifiers.jockeypack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LuckyModifier extends AbstractCardModifier {
    private static final String ID = makeID("LuckyModifier");
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return uiStrings.TEXT[0] + cardName;
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new LuckyModifier();
    }
}
