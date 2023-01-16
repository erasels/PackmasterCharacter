package thePackmaster.cards.ringofpainpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRingOfPainCard extends AbstractPackmasterCard
{
    public AbstractRingOfPainCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "ringofpain");

    }

    public AbstractRingOfPainCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"ringofpain");

    }
}
