package thePackmaster.cards.cosmoscommandpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCosmosCard extends AbstractPackmasterCard {
    public AbstractCosmosCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "cosmos");
    }
}
