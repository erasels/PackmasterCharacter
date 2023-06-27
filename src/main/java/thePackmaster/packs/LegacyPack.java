package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.legacypack.*;

import java.util.ArrayList;

public class LegacyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("LegacyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public LegacyPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 2, 2, 3, 2, "Exhaust"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AcidicSpray.ID);
        cards.add(BloodRitual.ID);
        cards.add(CaptainsCabin.ID);
        cards.add(StickStrike.ID);
        cards.add(MassProduction.ID);
        cards.add(OverTime.ID);
        cards.add(PoisonMastery.ID);
        cards.add(ScrapCannon.ID);
        cards.add(SlimeSpray.ID);
        cards.add(TerrorOfTheSeas.ID);
        cards.add(Cannonball.ID);
        return cards;
    }
}
