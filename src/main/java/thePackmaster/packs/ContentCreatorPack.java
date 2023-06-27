package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.contentcreatorpack.*;

import java.util.ArrayList;

public class ContentCreatorPack extends AbstractCardPack{
    public static final String ID = SpireAnniversary5Mod.makeID("ContentCreatorPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public ContentCreatorPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(2, 2, 4, 2, 4, "Orbs"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BaalorBash.ID);
        cards.add(BaalorBlueprint.ID);
        cards.add(DarksideSlap.ID);
        cards.add(Frostprime.ID);
        cards.add(HuttsGamble.ID);
        cards.add(OddOne.ID);
        cards.add(OlexasCloak.ID);
        cards.add(Retromation.ID);
        cards.add(Rhapsody.ID);
        cards.add(SneakyteakStrike.ID);
        cards.add(TheCozyChair.ID);
        cards.add(Tricky.ID);
        cards.add(Wanderbots.ID);
        return cards;
    }

}
