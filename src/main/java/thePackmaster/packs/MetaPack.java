package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.metapack.*;

import java.util.ArrayList;

public class MetaPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("MetaPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MetaPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 2, 2, 3, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ShedWeight.ID);
        cards.add(RayOfFrost.ID);
        cards.add(Swipe.ID);
        cards.add(Ambition.ID);
        cards.add(Diehard.ID);
        cards.add(EldritchBlast.ID);
        cards.add(DeathWish.ID);
        cards.add(KillerInstinct.ID);
        cards.add(Bulwark.ID);
        cards.add(Masterstroke.ID);
        return cards;
    }
}

