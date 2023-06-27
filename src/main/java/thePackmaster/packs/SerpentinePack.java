package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.serpentinepack.*;
import thePackmaster.cards.shamanpack.*;

import java.util.ArrayList;

public class SerpentinePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SerpentinePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    //public static final String CREDITS = UI_STRINGS.TEXT[3];

    public SerpentinePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 1, 3, 2, 4, "Stances", "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(PoisonedBlades.ID);
        cards.add(Guile.ID);
        cards.add(Toxicology.ID);
        cards.add(Patience.ID);
        cards.add(CheapShot.ID);
        cards.add(EvasiveTactics.ID);
        cards.add(BrutalStrikes.ID);
        cards.add(SinisterConcoction.ID);
        cards.add(ColdCalculated.ID);
        cards.add(DaggerRush.ID);

        return cards;
    }
}
