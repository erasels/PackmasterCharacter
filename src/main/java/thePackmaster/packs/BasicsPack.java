package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.basicspack.*;

import java.util.ArrayList;

public class BasicsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BasicsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BasicsPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(IroncladKit.ID);
        cards.add(SilentKit.ID);
        cards.add(DefectKit.ID);
        cards.add(WatcherKit.ID);
        cards.add(PackmasterKit.ID);
        cards.add(BasicSupply.ID);
        cards.add(SimplifiedStrike.ID);
        cards.add(PerfectBasics.ID);
        cards.add(Simplify.ID);
        cards.add(BackToBasics.ID);
        return cards;
    }
}
