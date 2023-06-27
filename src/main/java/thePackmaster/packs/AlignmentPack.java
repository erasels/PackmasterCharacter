package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.alignmentpack.*;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import java.util.ArrayList;

public class AlignmentPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("AlignmentPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public AlignmentPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 3, 5, 2, 2, "Exhaust", "Discard"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Conjunction.ID);
        cards.add(Opposition.ID);
        cards.add(Divination.ID);
        cards.add(Quincunx.ID);
        cards.add(Square.ID);
        cards.add(Novile.ID);
        cards.add(Glimpse.ID);
        cards.add(Remember.ID);
        cards.add(Trine.ID);
        cards.add(Alignment.ID);
        return cards;
    }

    @Override
    public PackPreviewCard makePreviewCard() {
        PackPreviewCard c = super.makePreviewCard();
        AnimatedCardsPatch.loadFrames(c, AbstractPackmasterCard.getCardTextureString("AlignmentPack", AbstractCard.CardType.SKILL), 15, 0.08F);
        return c;
    }
}