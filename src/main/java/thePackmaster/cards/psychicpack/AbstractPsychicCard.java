package thePackmaster.cards.psychicpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPsychicCard extends AbstractPackmasterCard
{
    public AbstractPsychicCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "psychic");

    }
    public AbstractPsychicCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color,"psychic");

    }
    @Override
    public void upp() {
    }
}
