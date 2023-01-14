package thePackmaster.util.cardvars;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = "<class>"
)
public class HoardField {
    public static SpireField<Integer> hoard = new SpireField(() -> {
        return -1;
    });
    public static SpireField<Integer> baseHoard = new SpireField(() -> {
        return -1;
    });
    public static SpireField<Boolean> isHoardUpgraded = new SpireField(() -> {
        return false;
    });

    public HoardField() {
    }

    public static void setBaseValue(AbstractCard card, int amount) {
        baseHoard.set(card, amount);
        hoard.set(card, amount);
        card.initializeDescription();
    }

    public static void resetValueToBase(AbstractCard card) {
        HoardField.hoard.set(card, HoardField.baseHoard.get(card));
        card.initializeDescription();
    }

    public static void upgrade(AbstractCard card, int amount) {
        isHoardUpgraded.set(card, true);
        setBaseValue(card, (Integer)baseHoard.get(card) + amount);
        card.initializeDescription();
    }

    public static void decrement(AbstractCard card, int value) {
        hoard.set(card, hoard.get(card) - value);
        card.initializeDescription();
    }
}
