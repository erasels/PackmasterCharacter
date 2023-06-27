package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.bitingcoldpack.*;

import java.util.ArrayList;

public class BitingColdPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BitingColdPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BitingColdPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 2, 3, 3, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(FrigidBody.ID);
        cards.add(Frostburn.ID);
        cards.add(FrostedEdge.ID);
        cards.add(GrowingAffliction.ID);
        cards.add(IceShatter.ID);
        cards.add(InsultToInjury.ID);
        cards.add(Refrigerate.ID);
        cards.add(RelentlessHail.ID);
        cards.add(SnowedIn.ID);
        cards.add(Subdue.ID);
        return cards;
    }
}