package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.entropypack.*;

import java.util.ArrayList;

public class EntropyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("EntropyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public EntropyPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 2, 3, 3, "Discard", "Debuffs", "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Disorder.ID);
        cards.add(Wither.ID);
        cards.add(Collapse.ID);
        cards.add(Construct.ID);
        cards.add(Freedom.ID);
        cards.add(RuinousPortent.ID);
        cards.add(Unbind.ID);
        cards.add(Diffusion.ID);
        cards.add(Waning.ID);
        cards.add(Entanglement.ID);
        return cards;
    }
}