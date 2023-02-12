package thePackmaster.cards.serpentinepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSerpentineCard extends AbstractPackmasterCard
{
    public AbstractSerpentineCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor col) {
        super(cardID, cost, type, rarity, target, col, "aggression");

    }

    public AbstractSerpentineCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    @Override
    public void upp() {
    }
}
