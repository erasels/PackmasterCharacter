package thePackmaster.cards.prismaticpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractPrismaticCard extends AbstractPackmasterCard
{
    public AbstractPrismaticCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "prismatic");

    }

    @Override
    public void upp() {
    }
}
