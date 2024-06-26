package thePackmaster.cards.bitingcoldpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class BitingColdCard extends AbstractPackmasterCard {
    public BitingColdCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "bitingcold");

    }
}
