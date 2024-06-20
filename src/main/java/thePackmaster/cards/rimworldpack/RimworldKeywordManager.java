package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;

public class RimworldKeywordManager {
    public static final String ID = SpireAnniversary5Mod.makeID(RimworldKeywordManager.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static String INSPIRATION_ID = cardStrings.EXTENDED_DESCRIPTION[0];
    public static String BREAK_ID = cardStrings.EXTENDED_DESCRIPTION[1];
}
