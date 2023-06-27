package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.orbpack.*;

import java.util.ArrayList;

public class OrbPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("OrbPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public OrbPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(1, 3, 4, 3, 4, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Allocate.ID);
        cards.add(Augment.ID);
        cards.add(Charge.ID);
        cards.add(Emptiness.ID);
        cards.add(Fear.ID);
        cards.add(IcicleCrash.ID);
        cards.add(MaterialBoost.ID);
        cards.add(Stockpile.ID);
        cards.add(Thunderstruck.ID);
        cards.add(Consume.ID);
        return cards;
    }

    @Override
    public PackPreviewCard makePreviewCard() {
        return new PackPreviewCard(packID, this, "blue/skill/chaos");
    }
}