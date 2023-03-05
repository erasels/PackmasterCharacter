package thePackmaster.cards.bellordpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBellordCard extends AbstractPackmasterCard {
    public AbstractBellordCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "bellord");
    }
}
