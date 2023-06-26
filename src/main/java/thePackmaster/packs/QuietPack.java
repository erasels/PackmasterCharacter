package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.downfallpack.*;
import thePackmaster.cards.quietpack.*;

import java.util.ArrayList;

public class QuietPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("QuietPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public QuietPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 4, 4, 4, 2, "Discard"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Crunch.ID);
        cards.add(Flail.ID);
        cards.add(Frontflip.ID);
        cards.add(GlaiveThrow.ID);
        cards.add(HammerThrow.ID);
        cards.add(IronShield.ID);
        cards.add(Lie.ID);
        cards.add(Oppressor.ID);
        cards.add(Shine.ID);
        cards.add(Weight.ID);
        cards.add(Wisdom.ID);
        return cards;
    }
}
