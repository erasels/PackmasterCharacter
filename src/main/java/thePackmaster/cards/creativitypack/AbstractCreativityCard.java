package thePackmaster.cards.creativitypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCreativityCard extends AbstractPackmasterCard
{
    public AbstractCreativityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target,"creativity");
    }
}
