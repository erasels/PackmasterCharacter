package thePackmaster.cards.marisapack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractMarisaCard extends AbstractPackmasterCard
{
    public AbstractMarisaCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "marisapack", "marisapack/orb.png");

    }
    public AbstractMarisaCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "marisapack","marisapack/orb.png");

    }
}
