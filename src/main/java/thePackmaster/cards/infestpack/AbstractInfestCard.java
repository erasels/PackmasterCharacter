package thePackmaster.cards.infestpack;

import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class AbstractInfestCard extends AbstractPackmasterCard {
    public AbstractInfestCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "infest");

    }
}
