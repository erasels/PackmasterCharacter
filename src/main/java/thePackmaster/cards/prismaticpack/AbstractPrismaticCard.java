package thePackmaster.cards.prismaticpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPrismaticCard extends AbstractPackmasterCard
{
    public AbstractPrismaticCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "prismatic");

    }

    @Override
    public void upp() {
    }
}
