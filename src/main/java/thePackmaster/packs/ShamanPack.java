package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.shamanpack.*;

import java.util.ArrayList;

public class ShamanPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ShamanPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public ShamanPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 3, 2, 1, 4, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Flare.ID);
        cards.add(FlameBlast.ID);
        cards.add(IceWall.ID);
        cards.add(FreezeAndBurn.ID);
        cards.add(Frostfire.ID);
        cards.add(FieryMantle.ID);
        cards.add(SnowMelt.ID);
        cards.add(Pyromastery.ID);
        cards.add(ReadTheFlames.ID);
        cards.add(FueledByEmbers.ID);
        cards.add(FadingEmber.ID);
        return cards;
    }
}
