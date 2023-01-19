package thePackmaster.cards.orbpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractOrbCard extends AbstractPackmasterCard
{
    public AbstractOrbCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "orbs");

    }
}
