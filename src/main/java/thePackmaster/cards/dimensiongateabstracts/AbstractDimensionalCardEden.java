package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardEden extends AbstractDimensionalCard {

    public AbstractDimensionalCardEden(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/eden");
    }

    public AbstractDimensionalCardEden(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/eden", color);
    }

}