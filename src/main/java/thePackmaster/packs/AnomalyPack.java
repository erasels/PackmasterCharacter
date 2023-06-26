package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.anomalypack.*;

import java.util.ArrayList;

public class AnomalyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("AnomalyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public AnomalyPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 3, 4, 1, 4, "Exhaust", "Discard"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Wonder.ID);
        cards.add(StrikeThroughFate.ID);
        cards.add(FreeSpace.ID);
        cards.add(MidnightOil.ID);
        cards.add(GoldenGun.ID);
        cards.add(GoldenRound.ID);
        cards.add(RitualSight.ID);
        cards.add(MindOverMatter.ID);
        cards.add(Thoughtweaving.ID);
        cards.add(SealedSword.ID);
        cards.add(SouleaterStrike.ID);
        cards.add(VoraciousSigil.ID);
        return cards;
    }
}
