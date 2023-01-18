package thePackmaster.cards.contentcreatorpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractContentCard extends AbstractPackmasterCard {
    public AbstractContentCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "content");

    }
    public AbstractContentCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "content");

    }
}
