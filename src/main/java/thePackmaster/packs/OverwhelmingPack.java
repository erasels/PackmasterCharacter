package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.overwhelmingpack.*;

import java.util.ArrayList;

public class OverwhelmingPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("OverwhelmingPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public OverwhelmingPack() {
        super(ID, NAME, DESC, AUTHOR);
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Lariat.ID);
        cards.add(GigaDrill.ID);
        cards.add(OpeningStrike.ID);
        cards.add(Withstand.ID);
        cards.add(Approach.ID);
        cards.add(SheerEffort.ID);
        cards.add(Overcome.ID);
        cards.add(FurtherBeyond.ID);
        cards.add(MakeRoom.ID);
        cards.add(FinalForm.ID);
        return cards;
    }
}
