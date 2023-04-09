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
        super(ID, NAME, DESC, AUTHOR, CREDITS);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SmitingStrike.ID);
        cards.add(GrievousIgnite.ID);
        cards.add(WitheringExhaust.ID);
        cards.add(ClutchTeleport.ID);
        cards.add(DualHeal.ID);
        cards.add(SpiritBarrier.ID);
        cards.add(PerfectClarity.ID);
        cards.add(ArtfulGhost.ID);
        cards.add(CleansingFountain.ID);
        cards.add(Flash.ID);
        return cards;
    }
}
