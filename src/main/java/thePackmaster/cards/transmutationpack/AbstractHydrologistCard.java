package thePackmaster.cards.transmutationpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractHydrologistCard extends AbstractPackmasterCard
{
    public AbstractHydrologistCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/hydrologist/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/hydrologist/" + type.name().toLowerCase() + ".png"
        );
        setOrbTexture(
                "anniv5Resources/images/512/hydrologist/orb.png",
                "anniv5Resources/images/1024/hydrologist/orb.png"
        );
    }
}
