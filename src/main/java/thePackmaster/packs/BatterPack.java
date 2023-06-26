package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.batterpack.*;

import java.util.ArrayList;

public class BatterPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BatterPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public BatterPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 2, 2, 4, 2, "Attacks"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BatterUp.ID);
        cards.add(Homerun.ID);
        cards.add(WideAngle.ID);
        cards.add(WindUp.ID);
        cards.add(OpenBracket.ID);
        cards.add(Tragedy.ID);
        cards.add(OptimizedBlur.ID);
        cards.add(HardCaramel.ID);
        cards.add(UltimateHomerun.ID);
        cards.add(AvatarBeat.ID);
        return cards;
    }
}

