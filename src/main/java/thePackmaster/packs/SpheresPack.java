package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.spherespack.*;

import java.util.ArrayList;

public class SpheresPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SpheresPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public SpheresPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 3, 1, 4, 3, "Debuffs", "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Ignition.ID);
        cards.add(DualBlade.ID);
        cards.add(Sphere.ID);
        cards.add(Winterwisp.ID);
        cards.add(FrozenMagma.ID);
        cards.add(Consolidate.ID);
        cards.add(FrostConversion.ID);
        cards.add(Fluctuate.ID);
        cards.add(VoidEngine.ID);
        cards.add(ChaosGenerator.ID);
        return cards;
    }
}
