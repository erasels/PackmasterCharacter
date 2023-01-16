package thePackmaster.cards.monsterhunterpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractMonsterHunterCard extends AbstractPackmasterCard
{
    public AbstractMonsterHunterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/monsterhunter/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/monsterhunter/" + type.name().toLowerCase() + ".png"
        );

    }

    public AbstractMonsterHunterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}