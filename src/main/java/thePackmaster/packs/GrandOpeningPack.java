package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.*;
import thePackmaster.cards.grandopeningpack.*;

import java.util.ArrayList;

public class GrandOpeningPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GrandOpeningPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public GrandOpeningPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 3, 3, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BattlePrep.ID);
        cards.add(Cross.ID);
        cards.add(DashIn.ID);
        cards.add(InstinctiveThinking.ID);
        cards.add(Debut.ID);
        cards.add(MessUp.ID);
        cards.add(Improvise.ID);
        cards.add(PlannedDefense.ID);
        cards.add(SecondStrike.ID);
        cards.add(Surge.ID);
        return cards;
    }
}
