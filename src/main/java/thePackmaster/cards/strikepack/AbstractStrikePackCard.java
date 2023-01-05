package thePackmaster.cards.strikepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractStrikePackCard extends AbstractPackmasterCard
{
    public AbstractStrikePackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/strikes/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/strikes/" + type.name().toLowerCase() + ".png"
        );
    }
}
