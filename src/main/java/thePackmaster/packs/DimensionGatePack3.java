package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack3.*;

import java.util.ArrayList;

public class DimensionGatePack3 extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionGate3Pack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public DimensionGatePack3() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 2, 4, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Corset.ID);
        cards.add(Inferno.ID);
        cards.add(Jam.ID);
        cards.add(Knighthood.ID);
        cards.add(MantisStrike.ID);
        cards.add(Minniegun.ID);
        cards.add(Ouroboros.ID);
        cards.add(RestorationDetonation.ID);
        cards.add(SelfWound.ID);
        cards.add(SignInBlood.ID);
        cards.add(SpreadingSpores.ID);
        return cards;
    }
}
