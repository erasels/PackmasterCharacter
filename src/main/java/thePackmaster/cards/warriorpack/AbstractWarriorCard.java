package thePackmaster.cards.warriorpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWarriorCard extends AbstractPackmasterCard
{
    public AbstractWarriorCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "warrior");

    }
    @Override
    public void upp() {
    }
}
