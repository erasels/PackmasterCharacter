package thePackmaster.cards.grandopeningpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractGrandOpeningCard extends AbstractPackmasterCard
{
    public AbstractGrandOpeningCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "grandopening");

    }
    @Override
    public void upp() {
    }
}
