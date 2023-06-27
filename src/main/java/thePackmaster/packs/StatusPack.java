package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.Overclock;
import com.megacrit.cardcrawl.cards.blue.Turbo;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.statuspack.DarkerEmbrace;

import java.util.ArrayList;

public class StatusPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("StatusPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public StatusPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 5, 5, 3, "Exhaust", "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(WildStrike.ID);
        cards.add(Turbo.ID);
        cards.add(TrueGrit.ID);
        cards.add(RecklessCharge.ID);
        cards.add(PowerThrough.ID);
        cards.add(Overclock.ID);
        cards.add(Evolve.ID);
        cards.add(FireBreathing.ID);
        cards.add(Immolate.ID);
        cards.add(DarkerEmbrace.ID);
        return cards;
    }
}

