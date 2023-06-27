package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.sentinelpack.*;

import java.util.ArrayList;

public class SentinelPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SentinelPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public SentinelPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 4, 2, 4, 2, "Stances"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Relax.ID);
        cards.add(Fume.ID);
        cards.add(EmptyBody.ID);
        cards.add(FlurryOfBlows.ID);
        cards.add(StanceDance.ID);
        cards.add(MoodSwing.ID);
        cards.add(MentalFortress.ID);
        cards.add(Serenity.ID);
        cards.add(RagnarokNRoll.ID);
        cards.add(Swagger.ID);
        return cards;
    }


}
