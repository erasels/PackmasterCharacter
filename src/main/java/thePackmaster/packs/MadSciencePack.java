package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.madsciencepack.Sharpen;

import java.util.ArrayList;

public class MadSciencePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("MadSciencePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];

    public MadSciencePack() {
        super(ID, NAME, DESC);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Sharpen.ID);
        return cards;
    }
}
