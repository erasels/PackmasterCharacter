package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.WitchesStrike.*;

import java.util.ArrayList;

public class WitchesStrikePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WitchesStrikePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];
    public WitchesStrikePack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 2, 4, 3, 3, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CrescentSweep.ID);
        cards.add(MysticFlourish.ID);
        cards.add(CrystalMoonlight.ID);
        cards.add(MoonlightBarrage.ID);
        cards.add(HornetWithin.ID);
        cards.add(ChitteringPunt.ID);
        cards.add(ScarabPlague.ID);
        cards.add(MoonlightFlight.ID);
        cards.add(WitchTwist.ID);
        cards.add(FullMoonHalo.ID);
        return cards;
    }
}
