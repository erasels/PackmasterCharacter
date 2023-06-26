package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.graveyardpack.*;

import java.util.ArrayList;

public class GraveyardPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GraveyardPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public GraveyardPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 1, 4, 3, 4, "Exhaust"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(GreyBargain.ID);
        cards.add(ExtraLimbs.ID);
        cards.add(AncientSpear.ID);
        cards.add(FleshLikeOak.ID);
        cards.add(CallOfTheGrave.ID);
        cards.add(SpontaneousRitual.ID);
        cards.add(FuneraryJewels.ID);
        cards.add(Tombstone.ID);
        cards.add(DarkOfNight.ID);
        cards.add(LastGasp.ID);
        return cards;
    }
}
