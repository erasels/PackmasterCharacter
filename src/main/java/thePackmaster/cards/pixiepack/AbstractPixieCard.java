package thePackmaster.cards.pixiepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPixieCard extends AbstractPackmasterCard
{
    public AbstractPixieCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "pixie");

    }

    @Override
    public void upp() {
    }
}
