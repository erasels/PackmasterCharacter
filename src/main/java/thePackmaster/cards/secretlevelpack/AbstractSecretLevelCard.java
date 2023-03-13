package thePackmaster.cards.secretlevelpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSecretLevelCard extends AbstractPackmasterCard {
    public AbstractSecretLevelCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "secretlevel");
    }
}
