package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.intothebreachpack.*;

import java.util.ArrayList;

public class IntoTheBreachPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("IntoTheBreachPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public IntoTheBreachPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 3, 3, 3, 1));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AcidProjector.ID);
        cards.add(ElectricWhip.ID);
        cards.add(EnrageShot.ID);
        cards.add(MercuryFist.ID);
        cards.add(ReboundVolley.ID);
        cards.add(SmokePellets.ID);
        cards.add(SmolderingShell.ID);
        cards.add(SpartanShield.ID);
        cards.add(StormGenerator.ID);
        cards.add(VoidShocker.ID);
        return cards;
    }
}