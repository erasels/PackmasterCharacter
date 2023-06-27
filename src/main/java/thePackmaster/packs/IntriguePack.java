package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.intriguepack.*;
import thePackmaster.cards.quantapack.*;

import java.util.ArrayList;

public class IntriguePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("IntriguePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public IntriguePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 2, 1));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Topple.ID);
        cards.add(Vanguard.ID);
        cards.add(Shakedown.ID);
        cards.add(Favor.ID);
        cards.add(EtTu.ID);
        cards.add(Spotlight.ID);
        cards.add(Exultation.ID);
        cards.add(Radiance.ID);
        cards.add(Apex.ID);
        cards.add(PowerStruggle.ID);
        return cards;
    }
}

