package thePackmaster.cards.rimworldpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRimworldCard extends AbstractPackmasterCard
{
    public AbstractRimworldCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "rimworld");

    }
    public AbstractRimworldCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"rimworld");

    }
    @Override
    public void upp() {
    }
}
