package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.metapack.*;
import thePackmaster.cards.wardenpack.*;

import java.util.ArrayList;

public class WardenPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WardenPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public WardenPack() {
        super(ID, NAME, DESC, AUTHOR,CREDITS, new AbstractCardPack.PackSummary(3, 2, 4, 2, 3));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(PierceVeil.ID);
        cards.add(Stall.ID);
        cards.add(RiskOfBlades.ID);
        cards.add(Crossroads.ID);
        cards.add(Anticipation.ID);
        cards.add(Prediction.ID);
        cards.add(ForceBolt.ID);
        cards.add(Enigmancy.ID);
        cards.add(Recurrence.ID);
        cards.add(DivineMight.ID);
        return cards;
    }
}

