package thePackmaster.cards.calamitypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCalamityCard extends AbstractPackmasterCard
{
    public AbstractCalamityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "shaman"); // Temporary -- will be switched to its own card back when ready
    }

    @Override
    public void upp() {
    }
}
