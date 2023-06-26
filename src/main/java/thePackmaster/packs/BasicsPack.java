package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.basicspack.*;

import java.util.ArrayList;

public class BasicsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BasicsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BasicsPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 2, 4, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BackToBasic.ID);
        cards.add(OckhamsRazor.ID);
        cards.add(SimpleTrick.ID);
        cards.add(BasicWave.ID);
        cards.add(FamiliarKit.ID);
        cards.add(Simplify.ID);
        cards.add(LoseIt.ID);
        cards.add(BasicHit.ID);
        cards.add(ConjureDefends.ID);
        cards.add(SimplifiedStrike.ID);
        return cards;
    }
}
