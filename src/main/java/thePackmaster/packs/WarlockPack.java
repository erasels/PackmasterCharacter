package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.warlockpack.*;

import java.util.ArrayList;

public class WarlockPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WarlockPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public WarlockPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HighPriestessJeklik.ID);
        cards.add(SoulShear.ID);
        cards.add(Soulfire.ID);
        cards.add(Doomguard.ID);
        cards.add(HandOfGulDan.ID);
        cards.add(SeedsOfDestruction.ID);
        cards.add(AranasiBroodmother.ID);
        cards.add(MalchezaarsImp.ID);
        cards.add(TheSoularium.ID);
        cards.add(RunedMithrilRod.ID);
        return cards;
    }
}
