package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack3.Inferno;
import thePackmaster.cards.dimensiongatepack3.Knighthood;
import thePackmaster.cards.dimensiongatepack3.Minniegun;

import java.util.ArrayList;

public class DimensionGatePack3 extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionGate3Pack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public DimensionGatePack3() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Minniegun.ID);
        cards.add(Inferno.ID);
        cards.add(Knighthood.ID);
        return cards;
    }
}
