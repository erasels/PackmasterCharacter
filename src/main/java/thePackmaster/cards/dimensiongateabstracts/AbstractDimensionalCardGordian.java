package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardGordian extends AbstractDimensionalCard {

    public AbstractDimensionalCardGordian(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/gordian");
    }

    public AbstractDimensionalCardGordian(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/gordian", color);
    }

}