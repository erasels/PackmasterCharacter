package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.Deflect;
import com.megacrit.cardcrawl.cards.green.EscapePlan;
import com.megacrit.cardcrawl.cards.red.Flex;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class AllForOnePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("AllForOnePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public AllForOnePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 3, 4, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Streamline.ID);
        cards.add(Flex.ID);
        cards.add(GoForTheEyes.ID);
        cards.add(BeamCell.ID);
        cards.add(FTL.ID);
        cards.add(EscapePlan.ID);
        cards.add(Scrape.ID);
        cards.add(Reprogram.ID);
        cards.add(MachineLearning.ID);
        cards.add(AllForOne.ID);
        return cards;
    }
}
