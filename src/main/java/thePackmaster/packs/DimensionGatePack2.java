package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack2.Anemia;
import thePackmaster.cards.dimensiongatepack2.LethalShot;

import java.util.ArrayList;

public class DimensionGatePack2 extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionGate2Pack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public DimensionGatePack2() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Anemia.ID);
        cards.add(LethalShot.ID);
        return cards;
    }
}
