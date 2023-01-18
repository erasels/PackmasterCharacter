package thePackmaster.cards.quietpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractQuietCard extends AbstractPackmasterCard
{
    public AbstractQuietCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "quiet");

    }
    public AbstractQuietCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"quiet");

    }
    @Override
    public void upp() {
    }
}
