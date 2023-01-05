package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.legacypack.*;

import java.util.ArrayList;

public class LegacyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("LegacyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public LegacyPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AcidicSpray.ID);
        cards.add(BloodRitual.ID);
        cards.add(CaptainsCabin.ID);
        cards.add(HuntressEssence.ID);
        cards.add(MassProduction.ID);
        cards.add(OverTime.ID);
        cards.add(PoisonMastery.ID);
        cards.add(ScrapCannon.ID);
        cards.add(SlimeSpray.ID);
        cards.add(TerrorOfTheSeas.ID);
        cards.add(Cannonball.ID);
        return cards;
    }
}
