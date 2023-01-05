package thePackmaster.cards.bitingcoldpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class BitingColdCard extends AbstractPackmasterCard {
    public BitingColdCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/bitingcold/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/bitingcold/" + type.name().toLowerCase() + ".png"
        );
    }
}
