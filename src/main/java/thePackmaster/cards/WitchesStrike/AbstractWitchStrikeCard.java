package thePackmaster.cards.WitchesStrike;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWitchStrikeCard extends AbstractPackmasterCard {
    public AbstractWitchStrikeCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "witchstrike");
    }
}
