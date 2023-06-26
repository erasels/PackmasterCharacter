package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.strikepack.*;

import java.util.ArrayList;

public class StrikesPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("StrikesPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public StrikesPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 1, 2, 4, 1, "Orbs", "Attacks"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Backstrike.ID);
        cards.add(DifferentStrikes.ID);
        cards.add(LightningStrike.ID);
        cards.add(PenaltyStrike.ID);
        cards.add(StrikeABargain.ID);
        cards.add(StrikeDummyJr.ID);
        cards.add(StrikeOfGenius.ID);
        cards.add(StrikeOfLuck.ID);
        cards.add(StrikeOfMidnight.ID);
        cards.add(WorkersStrike.ID);
        return cards;
    }
}
