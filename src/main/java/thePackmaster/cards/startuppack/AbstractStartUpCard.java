package thePackmaster.cards.startuppack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractStartUpCard extends AbstractPackmasterCard
{
    public AbstractStartUpCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "startup");

    }
}
