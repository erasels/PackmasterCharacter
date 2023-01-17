package thePackmaster.cards.odditiespack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractOdditiesCard extends AbstractPackmasterCard
{
    public AbstractOdditiesCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "oddities");

    }
    public AbstractOdditiesCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "oddities");

    }
}
