package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;

public class BoardGameKeywordManager {

    public static final String ID = SpireAnniversary5Mod.makeID(BoardGameKeywordManager.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static String DICE_ID = cardStrings.EXTENDED_DESCRIPTION[0];
    public static String CHANCE_ID = cardStrings.EXTENDED_DESCRIPTION[1];
}
