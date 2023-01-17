package thePackmaster.cards.distortionpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDistortionCard extends AbstractPackmasterCard
{
    public AbstractDistortionCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "blackhole");

    }
}
