package thePackmaster.cards.legacypack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractLegacyCard extends AbstractPackmasterCard
{
    public AbstractLegacyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/legacy/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/legacy/" + type.name().toLowerCase() + ".png"
        );
    }

    public AbstractLegacyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
