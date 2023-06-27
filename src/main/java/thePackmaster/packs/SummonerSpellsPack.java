package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.summonerspellspack.*;

import java.util.ArrayList;

public class SummonerSpellsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SummonerSpellsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public SummonerSpellsPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 1, 4, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SnowballStrike.ID);
        cards.add(GrievousIgnite.ID);
        cards.add(WitheringExhaust.ID);
        cards.add(UnleashedSmite.ID);
        cards.add(DualHeal.ID);
        cards.add(DeftBarrier.ID);
        cards.add(PerfectClarity.ID);
        cards.add(GhostingMaelstrom.ID);
        cards.add(TeleportBot.ID);
        cards.add(Flash.ID);
        return cards;
    }
}
