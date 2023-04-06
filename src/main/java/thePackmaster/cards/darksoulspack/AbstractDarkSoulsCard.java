package thePackmaster.cards.darksoulspack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDarkSoulsCard extends AbstractPackmasterCard
{
    public AbstractDarkSoulsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "darksouls");
    }
}
