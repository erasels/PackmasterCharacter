package thePackmaster.cards.quantapack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractQuantaCard extends AbstractPackmasterCard
{
    public AbstractQuantaCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "quanta");

    }
    public AbstractQuantaCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"quanta");

    }
    @Override
    public void upp() {
    }
}
