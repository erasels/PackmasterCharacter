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
        cards.add(SmitingStrikeCard.ID);
        cards.add(GrievousIgniteCard.ID);
        cards.add(WitheringExhaustCard.ID);
        cards.add(ClutchTeleportCard.ID);
        cards.add(DuoHealCard.ID);
        cards.add(SpiritBarrierCard.ID);
        cards.add(PerfectClarityCard.ID);
        cards.add(ArtfulGhostCard.ID);



        //TODO
        return cards;
    }
}
