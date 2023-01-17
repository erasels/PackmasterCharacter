package thePackmaster.cards.bardinspirepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBardCard extends AbstractPackmasterCard
{
    public AbstractBardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "bard", "bard/orb.png");

    }
}
