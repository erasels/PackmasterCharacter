package thePackmaster.cards.shamanpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractShamanCard extends AbstractPackmasterCard
{
    public AbstractShamanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "shaman");

    }

    @Override
    public void upp() {
    }
}
