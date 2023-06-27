package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.cthulhupack.*;

import java.util.ArrayList;

public class CthulhuPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CthulhuPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public static int lunacyThisCombat;

    public CthulhuPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 4, 3, 1, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AllSeeing.ID);
        cards.add(BeyondTheStars.ID);
        cards.add(EndOfDays.ID);
        cards.add(FamilialCurse.ID);
        cards.add(FlailingTendril.ID);
        cards.add(GazeTheVoid.ID);
        cards.add(NamelessMist.ID);
        cards.add(PageOfTheDead.ID);
        cards.add(RlyehFhtagn.ID);
        cards.add(StarSpawn.ID);
        cards.add(Lunacy.ID);
        return cards;
    }
}
