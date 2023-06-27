package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.utilitypack.*;

import java.util.ArrayList;

public class UtilityPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("UtilityPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public UtilityPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(2, 2, 5, 2, 3, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Excitement.ID);
        cards.add(LayeredDefenses.ID);
        cards.add(PreemptiveStrike.ID);
        cards.add(PlasmaShield.ID);
        cards.add(Enrage.ID);
        cards.add(Whispers.ID);
        cards.add(Replenish.ID);
        cards.add(LesserHex.ID);
        cards.add(GreaterHex.ID);
        cards.add(Conjuration.ID);
        cards.add(RareStrike.ID);
        return cards;
    }
}
