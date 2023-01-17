package thePackmaster.cards.energyandechopack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractEchoCard extends AbstractPackmasterCard
{
    public AbstractEchoCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "echo");

    }
}
