package thePackmaster.cards.weaponspack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWeaponsPackCard extends AbstractPackmasterCard
{
    public AbstractWeaponsPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, final CardColor color, final String textureString) {
        super(cardID, cost, type, rarity, target, color, textureString,  "weaponspack", null);
    }

    public AbstractWeaponsPackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW, "weaponspack");
    }

    @Override
    public void upp() {
    }
}
