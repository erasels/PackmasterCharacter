package thePackmaster.cards.cthulhupack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCthulhuCard extends AbstractPackmasterCard
{
    public AbstractCthulhuCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "cthulhu");

    }
}
