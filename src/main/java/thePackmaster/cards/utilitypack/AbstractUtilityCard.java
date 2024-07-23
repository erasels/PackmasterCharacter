package thePackmaster.cards.utilitypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractUtilityCard extends AbstractPackmasterCard
{
    public AbstractUtilityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "utility");

    }

    @Override
    public void upp() {
    }
}
