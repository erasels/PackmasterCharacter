package thePackmaster.cards.anomalypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractAnomalyCard extends AbstractPackmasterCard {
    public AbstractAnomalyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "anomaly");
    }

    public AbstractAnomalyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }
}
