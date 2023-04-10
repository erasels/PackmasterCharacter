package thePackmaster.cards.colorlesspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractColorlessPackCard extends AbstractPackmasterCard {
    public AbstractColorlessPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "colorless");
    }
}
