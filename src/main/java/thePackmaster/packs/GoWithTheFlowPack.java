package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.gowiththeflowpack.*;

import java.util.ArrayList;

public class GoWithTheFlowPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GoWithTheFlowPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public GoWithTheFlowPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(3, 3, 4, 3, 2, PackSummary.Tags.Discard));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Aqueducts.ID);
        cards.add(Cascade.ID);
        cards.add(Hail.ID);
        cards.add(HighTide.ID);
        cards.add(Hydraulics.ID);
        cards.add(IcyFloe.ID);
        cards.add(Pneumatics.ID);
        cards.add(ReleaseValve.ID);
        cards.add(Reservoir.ID);
        cards.add(StormFront.ID);
        return cards;
    }
}
