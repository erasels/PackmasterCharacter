package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack.*;

import java.util.ArrayList;

public class DimensionGatePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionGatePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DimensionGatePack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Anemia.ID);
        cards.add(ArsenalGear.ID);
        cards.add(ConjureBarrage.ID);
        cards.add(DarkRitual.ID);
        cards.add(Inferno.ID);
        cards.add(Knighthood.ID);
        cards.add(LethalShot.ID);
        cards.add(Minniegun.ID);
        cards.add(PackRat.ID);
        cards.add(ScorchedEarth.ID);
        return cards;
    }
}
