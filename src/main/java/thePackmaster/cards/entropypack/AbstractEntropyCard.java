package thePackmaster.cards.entropypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractEntropyCard extends AbstractPackmasterCard {
    public AbstractEntropyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "blackhole");
    }
}
