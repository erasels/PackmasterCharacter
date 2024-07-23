package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardStorybook extends AbstractDimensionalCard {

    public AbstractDimensionalCardStorybook(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/storybook");
    }

    public AbstractDimensionalCardStorybook(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/storybook", color);
    }

}