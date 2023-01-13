package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.boardgamepack.*;
import thePackmaster.cards.rimworldpack.*;

import java.util.ArrayList;

public class RimworldPack  extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(RimworldPack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public RimworldPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Betrayal.ID);
        cards.add(Catharsis.ID);
        cards.add(ChainShotgun.ID);
        cards.add(Overwork.ID);
        cards.add(Relax.ID);
        cards.add(Sanguine.ID);
        cards.add(SpikeTrap.ID);
        cards.add(ThrowHorseshoe.ID);
        cards.add(TradeBeacon.ID);
        cards.add(BurningPassion.ID);
        return cards;
    }
}