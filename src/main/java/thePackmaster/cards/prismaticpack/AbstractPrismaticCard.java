package thePackmaster.cards.prismaticpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractPrismaticCard extends AbstractPackmasterCard
{
    public AbstractPrismaticCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture("anniv5Resources/images/512/prismatic/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/prismatic/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
    }
}
