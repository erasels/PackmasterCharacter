package thePackmaster.cards.sneckopack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSneckoCard extends AbstractPackmasterCard
{
    public AbstractSneckoCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "snecko");

    }

    @Override
    public void upp() {
    }
}
