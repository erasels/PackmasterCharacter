package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.coresetpack.*;

import java.util.ArrayList;

public class CoreSetPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CoreSetPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];

    public CoreSetPack() {
        super(ID, NAME, DESC);
    }

    @Override
    public ArrayList<String> getCards() {
        //TODO - Replace this with the real Core Set!
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BackpackSmack.ID);
        cards.add(BoosterTutor.ID);
        cards.add(DestinyDraw.ID);
        cards.add(Flick.ID);
        cards.add(MayhemForm.ID);
        cards.add(PackRip.ID);
        cards.add(Showoff.ID);
        cards.add(Sideboard.ID);
        cards.add(SmithingHammer.ID);
        cards.add(Synergize.ID);
        return cards;
    }
}
