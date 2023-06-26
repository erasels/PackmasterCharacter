package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.RestRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.sneckopack.*;

import java.util.ArrayList;

public class SneckoPack extends AbstractCardPack {

    public static final String ID = SpireAnniversary5Mod.makeID("SneckoPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public SneckoPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 1, 4, 3, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> result = new ArrayList<>();
        result.add(Gulp.ID);
        result.add(Shedding.ID);
        result.add(Sliiiiither.ID);
        result.add(SnazzySwagger.ID);
        result.add(SneckoEyes.ID);
        result.add(Sneckpot.ID);
        result.add(TailFlail.ID);
        result.add(Twitch.ID);
        result.add(WhipNSnapper.ID);
        result.add(Whirligig.ID);
        return result;
    }
}
