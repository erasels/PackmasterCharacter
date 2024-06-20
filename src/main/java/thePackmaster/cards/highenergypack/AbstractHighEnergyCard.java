package thePackmaster.cards.highenergypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractHighEnergyCard extends AbstractPackmasterCard {
    public AbstractHighEnergyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "highenergy");

    }
}
