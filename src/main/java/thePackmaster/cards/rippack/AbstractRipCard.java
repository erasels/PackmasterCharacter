package thePackmaster.cards.rippack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRipCard extends AbstractPackmasterCard {
    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/rip/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/rip/" + type.name().toLowerCase() + ".png"
        );
    }

    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color, String textureString) {
        super(cardID, cost, type, rarity, target, color, textureString);
        setBackgroundTexture(
                "anniv5Resources/images/512/rip/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/rip/" + type.name().toLowerCase() + ".png"
        );
    }
}
