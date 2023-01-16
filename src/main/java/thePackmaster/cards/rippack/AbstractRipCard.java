package thePackmaster.cards.rippack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRipCard extends AbstractPackmasterCard {
    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "rip");

    }

    public AbstractRipCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color, String textureString) {
        super(cardID, cost, type, rarity, target, color, textureString, "rip");

    }
}
