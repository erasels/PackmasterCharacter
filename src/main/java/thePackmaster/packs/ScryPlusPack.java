package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.scrypluspack.*;

import java.util.ArrayList;

public class ScryPlusPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CreativityPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ScryPlusPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Anticipate.ID);
        cards.add(ApocalypseNow.ID);
        cards.add(FortuneCookie.ID);
        cards.add(FutureShield.ID);
        cards.add(Khahiri.ID);
        cards.add(PredictedStrike.ID);
        cards.add(Prosperity.ID);
        cards.add(RedIncense.ID);
        cards.add(WavingIron.ID);
        cards.add(ScryingRed.ID);
        return cards;
    }
}
