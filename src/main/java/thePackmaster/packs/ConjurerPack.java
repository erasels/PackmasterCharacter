package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.aggressionpack.*;
import thePackmaster.cards.conjurerpack.*;
import thePackmaster.cards.utilitypack.*;

import java.util.ArrayList;

public class ConjurerPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ConjurerPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ConjurerPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 4, 2, 2, 3, "Debuffs"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AirCurrent.ID);
        cards.add(DustTornado.ID);
        cards.add(Enervate.ID);
        cards.add(Excavation.ID);
        cards.add(Geomorphology.ID);
        cards.add(GeothermalHeating.ID);
        cards.add(Lithosphere.ID);
        cards.add(MudFissure.ID);
        cards.add(RagingInferno.ID);
        cards.add(TectonicQuake.ID);
        return cards;
    }
}
