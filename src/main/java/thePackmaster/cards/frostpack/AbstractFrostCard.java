package thePackmaster.cards.frostpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractFrostCard extends AbstractPackmasterCard {
    public AbstractFrostCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "frost");
    }


    @Override
    public void upp() {
    }
}
