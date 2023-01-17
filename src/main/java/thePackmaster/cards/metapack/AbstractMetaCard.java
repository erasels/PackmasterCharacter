package thePackmaster.cards.metapack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractMetaCard extends AbstractPackmasterCard
{
    public AbstractMetaCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "meta");

    }
}
