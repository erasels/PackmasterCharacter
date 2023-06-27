package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.highenergypack.*;

import java.util.ArrayList;

public class HighEnergyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("HighEnergyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public HighEnergyPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 3, 3, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Trash.ID);
        cards.add(MiracleWorker.ID);
        cards.add(MultiTool.ID);
        cards.add(LastResort.ID);
        cards.add(LotsOfIronWaves.ID);
        cards.add(ManifestMeal.ID);
        cards.add(Investor.ID);
        cards.add(StruckByATrain.ID);
        cards.add(StormForm.ID);
        cards.add(SuperNova.ID);
        cards.add(Food.ID);
        return cards;
    }
}
