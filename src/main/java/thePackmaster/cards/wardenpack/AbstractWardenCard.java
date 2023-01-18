package thePackmaster.cards.wardenpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWardenCard extends AbstractPackmasterCard
{
    public AbstractWardenCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "warden");

    }
    public AbstractWardenCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"warden");

    }
    @Override
    public void upp() {
    }
}
