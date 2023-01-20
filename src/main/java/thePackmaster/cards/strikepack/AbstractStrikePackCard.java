package thePackmaster.cards.strikepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractStrikePackCard extends AbstractPackmasterCard
{
    public AbstractStrikePackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "strikes");

    }
}
