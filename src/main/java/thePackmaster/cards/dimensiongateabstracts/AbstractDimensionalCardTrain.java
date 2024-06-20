package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardTrain extends AbstractDimensionalCard {

    public AbstractDimensionalCardTrain(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/train");
    }

    public AbstractDimensionalCardTrain(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/train", color);
    }

}