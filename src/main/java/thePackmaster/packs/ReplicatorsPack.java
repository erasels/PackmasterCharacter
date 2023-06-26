package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.replicatorspack.*;

import java.util.ArrayList;

public class ReplicatorsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ReplicatorsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ReplicatorsPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 5, 2, 5, "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(FrontloadedBash.ID);
        cards.add(Preparation.ID);
        cards.add(SmallAdaptation.ID);
        cards.add(ExperimentalMerge.ID);
        cards.add(ExhaustiveWork.ID);
        cards.add(IterativeDesign.ID);
        cards.add(Xpansion.ID);
        cards.add(ReduceOverhead.ID);
        cards.add(MagicHand.ID);
        cards.add(EnergyDrink.ID);
        return cards;
    }
}