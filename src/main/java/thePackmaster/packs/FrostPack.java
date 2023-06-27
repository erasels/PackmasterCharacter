package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.Blizzard;
import com.megacrit.cardcrawl.cards.blue.ColdSnap;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.frostpack.*;

import java.util.ArrayList;

public class FrostPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("FrostPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public FrostPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 5, 3, 1, 4, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Arson.ID);
        cards.add(ExtendedStall.ID);
        cards.add(FlashFreeze.ID);
        cards.add(Plunge.ID);
        cards.add(ColdSnap.ID);
        cards.add(Blizzard.ID);
        cards.add(MegaChill.ID);
        cards.add(Snack.ID);
        cards.add(FrozenSnack.ID);
        cards.add(Hailstorm.ID);
        cards.add(Prime.ID);

        return cards;
    }
}
