package thePackmaster.cards.downfallpack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDownfallCard extends AbstractPackmasterCard
{
    public AbstractDownfallCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/downfall/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/downfall/" + type.name().toLowerCase() + ".png"
        );

    }

    public AbstractDownfallCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
