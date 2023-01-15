package thePackmaster.cards.conjurerpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class ConjurerCard extends AbstractPackmasterCard {
    public ConjurerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                makeImagePath("512/conjurer/" + type.name().toLowerCase() + ".png"),
                makeImagePath("1024/conjurer/" + type.name().toLowerCase() + ".png")
        );
    }

    public static String makeID(Class<?> className) {
        return SpireAnniversary5Mod.makeID(className.getSimpleName());
    }
}
