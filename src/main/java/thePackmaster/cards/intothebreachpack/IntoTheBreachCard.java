package thePackmaster.cards.intothebreachpack;

import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class IntoTheBreachCard extends AbstractPackmasterCard {
    public IntoTheBreachCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                makeImagePath("512/intothebreach/" + type.name().toLowerCase() + ".png"),
                makeImagePath("1024/intothebreach/" + type.name().toLowerCase() + ".png")
        );
    }
}
