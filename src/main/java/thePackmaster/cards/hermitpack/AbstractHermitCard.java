package thePackmaster.cards.hermitpack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractHermitCard extends AbstractPackmasterCard
{
    public AbstractHermitCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/hermit/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/hermit/" + type.name().toLowerCase() + ".png"
        );
        setOrbTexture(
                "anniv5Resources/images/512/hermit/orb.png",
                "anniv5Resources/images/1024/hermit/orb.png"
        );
    }

    public AbstractHermitCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
