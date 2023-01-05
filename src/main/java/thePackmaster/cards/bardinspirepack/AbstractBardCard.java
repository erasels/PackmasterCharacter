package thePackmaster.cards.bardinspirepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBardCard extends AbstractPackmasterCard
{
    public AbstractBardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/bard/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/bard/" + type.name().toLowerCase() + ".png"
        );
        setOrbTexture(
                "anniv5Resources/images/512/bard/orb.png",
                "anniv5Resources/images/1024/bard/orb.png"
        );
    }
}
