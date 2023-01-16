package thePackmaster.cards.arcanapack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractAstrologerCard extends AbstractPackmasterCard {
    public AbstractAstrologerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "astrologer","astrologer/orb.png");

    }

    public AbstractAstrologerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "astrologer","astrologer/orb.png");

    }
}
