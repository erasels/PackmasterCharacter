package thePackmaster.cards.cthulhupack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCthulhuCard extends AbstractPackmasterCard
{
    public AbstractCthulhuCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/cthulhu/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/cthulhu/" + type.name().toLowerCase() + ".png"
        );
    }
}
