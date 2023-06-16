package thePackmaster.cards.cthulhupack;

import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.AllSeeingPowerTriggerOnCard;
import thePackmaster.powers.cthulhupack.SanityPower;
import thePackmaster.util.Wiz;

public abstract class AbstractCthulhuCard extends AbstractPackmasterCard {
    public AbstractCthulhuCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "cthulhu");

    }
    public AbstractCthulhuCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);

    }

    public static void loseSanity(int amount) {
        Wiz.applyToSelf(new SanityPower(Wiz.p(), amount * -1));
        if (Wiz.p().hasPower(AllSeeingPowerTriggerOnCard.POWER_ID)) {
            Wiz.p().getPower(AllSeeingPowerTriggerOnCard.POWER_ID).onSpecificTrigger();
        }
    }
}
