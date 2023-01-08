package thePackmaster.cards.weaponspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWeaponsPackCard extends AbstractPackmasterCard
{
    public AbstractWeaponsPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/weaponspack/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/weaponspack/" + type.name().toLowerCase() + ".png"
        );
    }

    @Override
    public void upp() {
    }
}
