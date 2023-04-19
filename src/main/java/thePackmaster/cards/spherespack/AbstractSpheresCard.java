package thePackmaster.cards.spherespack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSpheresCard extends AbstractPackmasterCard
{
    public AbstractSpheresCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "spheres");
    }

    @Override
    public void upp() {
    }
}
