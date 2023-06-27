package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.odditiespack.*;

import java.util.ArrayList;

public class OdditiesPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("OdditiesPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public OdditiesPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 1, 5, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AutoBattler.ID);
        cards.add(Beacon.ID);
        cards.add(EightBall.ID);
        cards.add(FinalForm.ID);
        cards.add(LetsDraft.ID);
        cards.add(LetsPlay8Ball.ID);
        cards.add(MidnightStrike.ID);
        cards.add(ShiningStyle.ID);
        cards.add(SleeveUp.ID);
        cards.add(ZaHando.ID);
        cards.add(StylePoints.ID);
        return cards;
    }
}
