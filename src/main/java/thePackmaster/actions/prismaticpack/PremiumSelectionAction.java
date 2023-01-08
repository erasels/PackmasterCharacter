package thePackmaster.actions.prismaticpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.prismaticpack.PrismaticUtil;

import java.util.ArrayList;

public class PremiumSelectionAction extends AbstractChooseOneCardAction {
    private int amount;

    public PremiumSelectionAction(int amount) {
        super(1, true, 0);
        this.amount = amount;
    }

    @Override
    protected ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        int i = 0;
        while (i < this.amount) {
            AbstractCard card = PrismaticUtil.getRandomDifferentColorCardInCombat(null, AbstractCard.CardRarity.RARE, 1).get(0);
            if (cards.stream().noneMatch(c -> c.cardID.equals(card.cardID))) {
                cards.add(card);
                i++;
            }
        }
        return cards;
    }
}
