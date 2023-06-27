package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.bardinspirepack.*;

import java.util.ArrayList;

public class BardInspirePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BardInspirePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BardInspirePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(InspiringSong.ID);
        cards.add(Sift.ID);
        cards.add(EchoEcho.ID);
        cards.add(ThickOfTheFight.ID);
        cards.add(EnGarde.ID);
        cards.add(Heroism.ID);
        cards.add(LifeDrain.ID);
        cards.add(Shifting.ID);
        cards.add(MagnumOpus.ID);
        cards.add(SplendidForm.ID);
        return cards;
    }
}
