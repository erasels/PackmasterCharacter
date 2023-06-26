package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.upgradespack.*;

import java.util.ArrayList;

public class BlacksmithsPack extends AbstractCardPack{

    public static final String ID = SpireAnniversary5Mod.makeID("BlacksmithsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BlacksmithsPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 4, 4));
    }
    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(RedHotHammer.ID);
        cards.add(QuickFix.ID);
        cards.add(CarelessSwing.ID);
        cards.add(HeartOfTheForge.ID);
        cards.add(SharpeningSheath.ID);
        cards.add(SetUpShop.ID);
        cards.add(BlacksmithsShield.ID);
        cards.add(MastersStrike.ID);
        cards.add(ScrapMetal.ID);
        cards.add(Masterwork.ID);
        return cards;
    }
}
