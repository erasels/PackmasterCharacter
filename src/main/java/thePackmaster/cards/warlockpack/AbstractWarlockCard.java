package thePackmaster.cards.warlockpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWarlockCard extends AbstractPackmasterCard
{
    public AbstractWarlockCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "warlock", "warlock/orb.png");

    }
    public AbstractWarlockCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "warlock", "warlock/orb.png");

    }

    @Override
    public void upp() {
    }
}
