package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardRoguebook extends AbstractDimensionalCard {

    public AbstractDimensionalCardRoguebook(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/roguebook");
    }

    public AbstractDimensionalCardRoguebook(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/roguebook", color);
    }

}