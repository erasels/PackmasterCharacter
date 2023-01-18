package thePackmaster.cards.rimworldpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRimCard extends AbstractPackmasterCard {

    public AbstractRimCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "rimworld");

    }
}
