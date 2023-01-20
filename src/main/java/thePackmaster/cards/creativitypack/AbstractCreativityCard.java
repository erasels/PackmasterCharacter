package thePackmaster.cards.creativitypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCreativityCard extends AbstractPackmasterCard
{
    public AbstractCreativityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/creativity/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/creativity/" + type.name().toLowerCase() + ".png"
        );
    }
}
