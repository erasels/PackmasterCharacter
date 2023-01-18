package thePackmaster.cards.upgradespack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBlacksmithCard extends AbstractPackmasterCard
{
    public AbstractBlacksmithCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "blacksmith");

    }
    public AbstractBlacksmithCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"blacksmith");

    }
    @Override
    public void upp() {
    }
}
