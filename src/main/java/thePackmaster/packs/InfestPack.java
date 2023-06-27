package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.infestpack.*;

import java.util.ArrayList;

public class InfestPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("InfestPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public InfestPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 3, 2, 2));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BuggyBash.ID);
        cards.add(SwarmSword.ID);
        cards.add(GrassHop.ID);
        cards.add(Bzzzzz.ID);
        cards.add(BugOut.ID);
        cards.add(ScarabShield.ID);
        cards.add(Entomology.ID);
        cards.add(Compoundeyes.ID);
        cards.add(Bees.ID);
        cards.add(LordOfTheFlies.ID);
        return cards;
    }
}
