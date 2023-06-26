package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.transmutationpack.*;

import java.util.ArrayList;

public class TransmutationPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("TransmutationPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public TransmutationPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 3, 2));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AmalgamateWaters.ID);
        cards.add(CrystalLattice.ID);
        cards.add(CrystalResonance.ID);
        cards.add(DimensionalIcicles.ID);
        cards.add(Drench.ID);
        cards.add(FrozenCapsule.ID);
        cards.add(GhostlyMist.ID);
        cards.add(GlacialSynthesis.ID);
        cards.add(SearingSynthesis.ID);
        cards.add(ViscousShell.ID);
        return cards;
    }
}
