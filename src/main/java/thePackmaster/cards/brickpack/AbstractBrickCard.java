package thePackmaster.cards.brickpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBrickCard extends AbstractPackmasterCard
{
    public static final String FRAME_ID = null;

    public AbstractBrickCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, FRAME_ID);

    }
    public AbstractBrickCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, FRAME_ID);
    }
}
