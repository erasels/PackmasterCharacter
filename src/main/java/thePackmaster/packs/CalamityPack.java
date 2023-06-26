package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.calamitypack.*;

import java.util.ArrayList;

public class CalamityPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CalamityPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public CalamityPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(4, 2, 2, 3, 4, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Doomtouch.ID);
        cards.add(BindingChains.ID);
        cards.add(FreezingVenom.ID);
        cards.add(EssenceGrasp.ID);
        cards.add(ChillingCurse.ID);
        cards.add(GrowingFlames.ID);
        cards.add(Suffer.ID);
        cards.add(CobrasGift.ID);
        cards.add(ArmorOfMalice.ID);
        cards.add(AllIsFire.ID);
        return cards;
    }
}
