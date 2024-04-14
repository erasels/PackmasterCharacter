package thePackmaster.cards.showmanpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractShowmanCard extends AbstractPackmasterCard
{
    public AbstractShowmanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "showman");

    }

    public AbstractShowmanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}