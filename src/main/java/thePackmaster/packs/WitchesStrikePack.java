package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class WitchesStrikePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WitchesStrikePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public WitchesStrikePack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();

        return cards;
    }
}
