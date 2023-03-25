package thePackmaster.cards.fueledpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractFueledCard extends AbstractPackmasterCard
{
    public static final String FRAME_ID = "fueled";

    public AbstractFueledCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, FRAME_ID);

    }
    public AbstractFueledCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, FRAME_ID);

    }
}
