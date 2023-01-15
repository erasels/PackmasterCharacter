package thePackmaster.cards.rippack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRipCard extends AbstractPackmasterCard {
    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/rip/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/rip/" + type.name().toLowerCase() + ".png"
        );
    }

    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color, String textureString) {
        super(cardID, cost, type, rarity, target, color, textureString);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/rip/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/rip/" + type.name().toLowerCase() + ".png"
        );
    }
}
