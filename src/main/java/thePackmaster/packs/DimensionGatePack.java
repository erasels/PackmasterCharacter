package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack.*;
import thePackmaster.cards.dimensiongatepack2.*;

import java.util.ArrayList;

public class DimensionGatePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionGatePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public DimensionGatePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 2, 4, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Anemia.ID);
        cards.add(LethalShot.ID);
        cards.add(BleedItOut.ID);
        cards.add(BoosterShot.ID);
        cards.add(RangersSetup.ID);
        cards.add(Scheme.ID);
        cards.add(ScrollOfIntellect.ID);
        cards.add(Sustenance.ID);
        cards.add(TheEncyclopedia.ID);
        cards.add(TomeOfIntellect.ID);
        cards.add(Trace.ID);
        return cards;
    }
}
