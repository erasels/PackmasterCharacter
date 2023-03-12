package thePackmaster.cards.goddessofexplosionspack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractGoddessOfExplosionsCard extends AbstractPackmasterCard
{
    public AbstractGoddessOfExplosionsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "goddessofexplosions", "goddessofexplosions/orb.png");

    }

    public AbstractGoddessOfExplosionsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
