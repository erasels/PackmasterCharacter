package thePackmaster.cards.highenergypack;

import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class AbstractHighEnergyCard extends AbstractPackmasterCard {
    public AbstractHighEnergyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                makeImagePath("512/highenergy/" + type.name().toLowerCase() + ".png"),
                makeImagePath("1024/highenergy/" + type.name().toLowerCase() + ".png")
        );
    }
}
