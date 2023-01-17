package thePackmaster.cards.summonspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSummonsCard extends AbstractPackmasterCard
{
    public AbstractSummonsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "summoner");

    }
    public AbstractSummonsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "summoner");

    }
}
