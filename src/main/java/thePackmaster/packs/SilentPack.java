package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class SilentPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SilentPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public SilentPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 3, 3, "Discard"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BladeDance.ID);
        cards.add(Prepared.ID);
        cards.add(SneakyStrike.ID);
        cards.add(CloakAndDagger.ID);
        cards.add(CalculatedGamble.ID);
        cards.add(Finisher.ID);
        cards.add(Choke.ID);
        cards.add(Footwork.ID);
        cards.add(BulletTime.ID);
        cards.add(StormOfSteel.ID);
        return cards;
    }
}
