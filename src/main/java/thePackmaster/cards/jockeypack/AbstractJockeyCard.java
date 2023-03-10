package thePackmaster.cards.jockeypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractJockeyCard extends AbstractPackmasterCard {
    public AbstractJockeyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "secretlevel");
    }
}
