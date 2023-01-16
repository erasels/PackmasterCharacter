package thePackmaster.cards.graveyardpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractGraveyardCard extends AbstractPackmasterCard
{
    public AbstractGraveyardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/graveyard/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/graveyard/" + type.name().toLowerCase() + ".png"
        );

    }

    public AbstractGraveyardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
