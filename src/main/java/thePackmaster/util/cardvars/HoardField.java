package thePackmaster.util.cardvars;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = "<class>"
)
public class HoardField {
    public static SpireField<Integer> hoard = new SpireField<>(() -> -1);
    public static SpireField<Integer> baseHoard = new SpireField<>(() -> -1);
    public static SpireField<Boolean> isHoardUpgraded = new SpireField<>(() -> false);
    public static SpireField<Boolean> isHoardModified = new SpireField<>(() -> false);

    public HoardField() {
    }

    public static void setBaseValue(AbstractCard card, int amount) {
        baseHoard.set(card, amount);
        hoard.set(card, amount);
    }

    public static void resetValueToBase(AbstractCard card) {
        HoardField.hoard.set(card, HoardField.baseHoard.get(card));
        isHoardModified.set(card, false);
    }

    public static void upgrade(AbstractCard card, int amount) {
        isHoardUpgraded.set(card, true);
        baseHoard.set(card, baseHoard.get(card) + amount);
        if (hoard.get(card) <= 0) {
            //if for some reason hoard is not initialized right now, do it.
            hoard.set(card, baseHoard.get(card));
        } else {
            //in-combat upgrades (armaments) should not reset the hoard counter, but drop it down in tandem with the change to base.
            hoard.set(card, hoard.get(card) + amount);

            //...to a minimum value of 1.
            if (hoard.get(card) < 1) {
                hoard.set(card, 1);
            }
        }

    }

    public static void decrement(AbstractCard card, int value) {
        hoard.set(card, hoard.get(card) - value);

        isHoardModified.set(card, true);
    }
}
