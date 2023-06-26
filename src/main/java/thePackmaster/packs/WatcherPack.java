package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class WatcherPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WatcherPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public WatcherPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 3, 4, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SandsOfTime.ID);
        cards.add(FollowUp.ID);
        cards.add(PressurePoints.ID);
        cards.add(CrushJoints.ID);
        cards.add(ThirdEye.ID);
        cards.add(Fasting.ID);
        cards.add(Wallop.ID);
        cards.add(DeceiveReality.ID);
        cards.add(LessonLearned.ID);
        cards.add(Vault.ID);
        return cards;
    }
}
