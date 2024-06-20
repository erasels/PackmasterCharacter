package thePackmaster.cards.dimensiongateabstracts;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDimensionalCard extends AbstractPackmasterCard {
    public final static String ID = "";

    public AbstractDimensionalCard(final String cardID, final int cost, final CardRarity rarity, final AbstractCard.CardType type, final AbstractCard.CardTarget target, final String frameID) {
        super(cardID, cost, type, rarity, target, frameID);
    }

    public AbstractDimensionalCard(final String cardID, final int cost, final CardRarity rarity, final AbstractCard.CardType type, final AbstractCard.CardTarget target, final String frameID, final CardColor color) {
        super(cardID, cost, type, rarity, target, color, frameID);
    }

}