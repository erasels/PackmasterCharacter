package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.rippack.*;
import thePackmaster.cards.showmanpack.*;

import java.util.ArrayList;

public class ShowmanPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ShowmanPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ShowmanPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(3, 3, 2, 5, 2, PackSummary.Tags.Exhaust, PackSummary.Tags.Powers));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Grandstand.ID);
        cards.add(Dazzle.ID);
        cards.add(SeeMe.ID);
        cards.add(ForMyNextTrick.ID);
        cards.add(FlashPowder.ID);
        cards.add(Amaze.ID);
        cards.add(LeftBrain.ID);
        cards.add(SurprisePack.ID);
        cards.add(TotalWeakness.ID);
        cards.add(ViciousCycle.ID);
        return cards;
    }
}
