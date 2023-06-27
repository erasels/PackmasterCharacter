package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.downfallpack.*;
import thePackmaster.cards.farmerpack.*;

import java.util.ArrayList;

public class FarmerPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("FarmerPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public FarmerPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 2, 3, 2, 1));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HarvestCarrots.ID);
        cards.add(HarvestBeans.ID);
        cards.add(HarvestPotatoes.ID);
        cards.add(HarvestRadishes.ID);
        cards.add(Fertilizer.ID);
        cards.add(SoilResiliency.ID);
        cards.add(SowTheSeeds.ID);
        cards.add(TillTheEarth.ID);
        cards.add(OutToMarket.ID);
        cards.add(SupplyChain.ID);
        cards.add(PestControl.ID);
        return cards;
    }
}
