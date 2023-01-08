package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.eurogamepack.*;


import java.util.ArrayList;

public class EurogamePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("EurogamePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public EurogamePack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Auctioneering.ID);
        cards.add(NetFishing.ID);
        cards.add(QuickGame.ID);
        cards.add(Roadbuilding.ID);
        cards.add(Harvest.ID);
        cards.add(MilitaryExpansion.ID);
        cards.add(IronMining.ID);
        cards.add(SteelMilling.ID);
        cards.add(Prosper.ID);
        cards.add(LumberFelling.ID);
        return cards;
    }

}