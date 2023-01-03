package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.utilitypack.Excitement;
import thePackmaster.cards.utilitypack.LayeredDefenses;
import thePackmaster.cards.utilitypack.PlasmaShield;
import thePackmaster.cards.utilitypack.PreemptiveStrike;

import java.util.ArrayList;

public class UtilityPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("UtilityPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public UtilityPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Excitement.ID);
        cards.add(LayeredDefenses.ID);
        cards.add(PreemptiveStrike.ID);
        cards.add(PlasmaShield.ID);
        return cards;
    }
}
