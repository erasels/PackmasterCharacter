package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.hermitpack.*;
import thePackmaster.cards.quantapack.MissileStrike;

import java.util.ArrayList;

public class HermitPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("HermitPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public HermitPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 3, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Quickdraw.ID);
        cards.add(Glare.ID);
        cards.add(HoleUp.ID);
        cards.add(Headshot.ID);
        cards.add(Virtue.ID);
        cards.add(FlashPowder.ID);
        cards.add(Determination.ID);
        cards.add(Purgatory.ID);
        cards.add(OverwhelmingPower.ID);
        cards.add(DeadOrAlive.ID);
        return cards;
    }
}

