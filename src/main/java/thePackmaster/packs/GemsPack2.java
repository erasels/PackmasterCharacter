package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.gemspack.*;
import thePackmaster.cards.gemspack2.*;

import java.util.ArrayList;

public class GemsPack2 extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GemsPack2");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public GemsPack2() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ChargeUpGem.ID);
        cards.add(DiceGem.ID);
        cards.add(EntropyGem.ID);
        cards.add(FrostbiteGem.ID);
        cards.add(InspirationGem.ID);
        cards.add(OccultGem.ID);
        cards.add(PersistGem.ID);
        cards.add(PetraGem.ID);
        cards.add(RendGem.ID);
        cards.add(RuinGem.ID);
        return cards;
    }

}