package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.prismaticpack.*;

import java.util.ArrayList;

public class PrismaticPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PrismaticPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public PrismaticPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 3, 4, 2, 3, "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SwordAndDagger.ID);
        cards.add(PrismaticBarrier.ID);
        cards.add(GrabBag.ID);
        cards.add(ShimmeringStrike.ID);
        cards.add(ExoticKnowledge.ID);
        cards.add(ChoiceCut.ID);
        cards.add(QuirionDryad.ID);
        cards.add(ManyAsOne.ID);
        cards.add(PremiumSelection.ID);
        cards.add(FlawlessPrism.ID);
        return cards;
    }
}
