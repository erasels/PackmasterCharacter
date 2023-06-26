package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.instadeathpack.*;

import java.util.ArrayList;

public class InstantDeathPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("InstantDeathPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public InstantDeathPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 2, 3, "Attacks"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Needle.ID);
        cards.add(Lock.ID);
        cards.add(Curtain.ID);
        cards.add(Mirror.ID);
        cards.add(Nail.ID);
        cards.add(ThreadCard.ID);
        cards.add(Cloud.ID);
        cards.add(SlashDraw.ID);
        cards.add(PathToVictory.ID);
        cards.add(AnotherMoment.ID);
        return cards;
    }
}