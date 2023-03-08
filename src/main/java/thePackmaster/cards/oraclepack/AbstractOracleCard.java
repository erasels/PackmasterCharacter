package thePackmaster.cards.oraclepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractOracleCard extends AbstractPackmasterCard {

    public AbstractOracleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "oracle");

    }
    public AbstractOracleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "oracle");

    }
}
