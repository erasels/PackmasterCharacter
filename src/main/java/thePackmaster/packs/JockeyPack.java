package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.jockeypack.*;

import java.util.ArrayList;

public class JockeyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("JockeyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public JockeyPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 2, 4, 2, 4));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Horse.ID);
        cards.add(StartingGun.ID);
        cards.add(Trample.ID);
        cards.add(TakeTheLead.ID);
        cards.add(ReinIn.ID);
        cards.add(Homestretch.ID);
        cards.add(LuckyHorseshoe.ID);
        cards.add(Derby.ID);
        cards.add(Giddyap.ID);
        cards.add(TheSpin.ID);
        cards.add(OffToTheRaces.ID);
        return cards;
    }
}
