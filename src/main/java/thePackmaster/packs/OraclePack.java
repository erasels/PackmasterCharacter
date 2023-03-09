package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.odditiespack.*;
import thePackmaster.cards.oraclepack.*;

import java.util.ArrayList;

public class OraclePack extends AbstractCardPack {

    public static final String ID = SpireAnniversary5Mod.makeID("OraclePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public OraclePack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AppeaseFate.ID);
        cards.add(BadOmen.ID);
        cards.add(BrowsingFate.ID);
        cards.add(Doomsayer.ID);
        cards.add(ReadTheSigns.ID);
        cards.add(SeeingTheThreads.ID);
        cards.add(Untanglement.ID);
        return cards;
    }
}
