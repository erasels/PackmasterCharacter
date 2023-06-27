package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.bellordpack.*;

import java.util.ArrayList;

public class BellordPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BellordPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BellordPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 3, 2, 4, 1));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Reverberation.ID);
        cards.add(Midnight.ID);
        cards.add(Clang.ID);
        cards.add(VoDo.ID);
        cards.add(RingTheBell.ID);
        cards.add(TimeOut.ID);
        cards.add(Cuckoo.ID);
        cards.add(Dong.ID);
        cards.add(LikeClockwork.ID);
        cards.add(SavedByTheBell.ID);
        return cards;
    }
}
