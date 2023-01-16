package thePackmaster.cards.batterpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBatterCard extends AbstractPackmasterCard {
    public AbstractBatterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "batter");
    }
}
