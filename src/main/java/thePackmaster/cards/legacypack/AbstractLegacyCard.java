package thePackmaster.cards.legacypack;


import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractLegacyCard extends AbstractPackmasterCard
{
    public AbstractLegacyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/legacy/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/legacy/" + type.name().toLowerCase() + ".png"
        );
    }
}
