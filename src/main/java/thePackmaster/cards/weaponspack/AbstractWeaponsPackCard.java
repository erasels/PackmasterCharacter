package thePackmaster.cards.weaponspack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWeaponsPackCard extends AbstractPackmasterCard
{
    public AbstractWeaponsPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor col) {
        super(cardID, cost, type, rarity, target, col, "weaponspack");

    }

    public AbstractWeaponsPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    @Override
    public void upp() {
    }
}
