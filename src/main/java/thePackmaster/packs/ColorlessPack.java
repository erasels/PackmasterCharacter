package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.colorlesspack.*;

import java.util.ArrayList;

public class ColorlessPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ColorlessPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ColorlessPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 2, 4, "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Manifest.ID);
        cards.add(Bunker.ID);
        cards.add(GolfBall.ID);
        cards.add(Slapstick.ID);
        cards.add(Ghost.ID);
        cards.add(LunchBox.ID);
        cards.add(AdventurersSword.ID);
        cards.add(Triforce.ID);
        cards.add(ColorlessMastery.ID);
        cards.add(VisitAMonastery.ID);
        cards.add(ThePrism.ID);
        cards.add(PrismShard.ID);
        cards.add(RealityMaster.ID);
        return cards;
    }
}
