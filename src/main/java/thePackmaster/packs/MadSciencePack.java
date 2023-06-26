package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.madsciencepack.*;

import java.util.ArrayList;

public class MadSciencePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("MadSciencePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MadSciencePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 5, 2, 3, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Cheapen.ID);
        cards.add(Enhance.ID);
        cards.add(Expand.ID);
        cards.add(Fortify.ID);
        cards.add(Improve.ID);
        cards.add(Attach.ID);
        cards.add(Piratify.ID);
        cards.add(Sharpen.ID);
        cards.add(Discombobulate.ID);
        cards.add(Winterize.ID);
        return cards;
    }
}
