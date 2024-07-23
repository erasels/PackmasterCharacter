package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardObelisk extends AbstractDimensionalCard {

    public AbstractDimensionalCardObelisk(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/obelisk");
    }

    public AbstractDimensionalCardObelisk(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/obelisk", color);
    }


}