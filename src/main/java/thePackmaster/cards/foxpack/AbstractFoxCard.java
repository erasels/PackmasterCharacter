package thePackmaster.cards.foxpack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractFoxCard extends AbstractPackmasterCard
{
    public AbstractFoxCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/monsterhunter/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/monsterhunter/" + type.name().toLowerCase() + ".png"
        );

    }

    public AbstractFoxCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
