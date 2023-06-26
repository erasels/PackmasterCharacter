package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.quantapack.*;

import java.util.ArrayList;

public class QuantaPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("QuantaPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public QuantaPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 4, 3, 4, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(MissileStrike.ID);
        cards.add(MagicDart.ID);
        cards.add(Cull.ID);
        cards.add(Potential.ID);
        cards.add(Aura.ID);
        cards.add(MAXPOWER.ID);
        cards.add(Flow.ID);
        cards.add(Manaburst.ID);
        cards.add(Dominate.ID);
        cards.add(IchorGoblet.ID);
        return cards;
    }
}

