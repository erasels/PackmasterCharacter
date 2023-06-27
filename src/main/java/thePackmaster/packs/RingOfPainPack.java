package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.ringofpainpack.*;

import java.util.ArrayList;

public class RingOfPainPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("RingOfPainPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public RingOfPainPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 2, 3, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Hound.ID);
        cards.add(Slime.ID);
        cards.add(Fright.ID);
        cards.add(Shackles.ID);
        cards.add(IceBeast.ID);
        cards.add(Loombird.ID);
        cards.add(Hollower.ID);
        cards.add(Owl.ID);
        cards.add(Vision.ID);
        cards.add(Connection.ID);
        return cards;
    }
}
