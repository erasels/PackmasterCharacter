package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.rippack.*;

import java.util.ArrayList;

public class RipPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("RipPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public RipPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 2, 5, 2, "Exhaust", "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ArtAttack.ID);
        cards.add(DividedFury.ID);
        cards.add(FlimsyBash.ID);
        cards.add(FragileShrug.ID);
        cards.add(HazardousStrike.ID);
        cards.add(Inspiration.ID);
        cards.add(LeftBrain.ID);
        cards.add(SurprisePack.ID);
        cards.add(TotalWeakness.ID);
        cards.add(ViciousCycle.ID);
        return cards;
    }
}
