package thePackmaster.cards.downfallpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDownfallCard extends AbstractPackmasterCard
{
    public AbstractDownfallCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/downfall/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/downfall/" + type.name().toLowerCase() + ".png"
        );
    }
}
