package thePackmaster.cards.replicatorspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractReplicatorCard extends AbstractPackmasterCard
{
    public AbstractReplicatorCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "replicators");

    }

    @Override
    public void upp() {
    }
}

