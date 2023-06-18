package thePackmaster.cards.overwhelmingpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractOverwhelmingCard extends AbstractPackmasterCard {
    public AbstractOverwhelmingCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "overwhelming");
    }
}
