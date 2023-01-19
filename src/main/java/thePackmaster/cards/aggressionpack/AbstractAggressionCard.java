package thePackmaster.cards.aggressionpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractAggressionCard extends AbstractPackmasterCard
{
    public AbstractAggressionCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "aggression");
    }

    @Override
    public void upp() {
    }
}
