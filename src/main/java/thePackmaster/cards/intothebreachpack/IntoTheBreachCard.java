package thePackmaster.cards.intothebreachpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class IntoTheBreachCard extends AbstractPackmasterCard {
    public IntoTheBreachCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "intothebreach");

    }
}
