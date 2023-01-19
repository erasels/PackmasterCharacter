package thePackmaster.util.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class HoardVar extends DynamicVariable {
    @Override
    public String key() {
        return "hoard";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return HoardField.isHoardUpgraded.get(card);
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        HoardField.isHoardUpgraded.set(card, v);
    }

    @Override
    public int value(AbstractCard card) {
        return HoardField.hoard.get(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return HoardField.hoard.get(card);
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return HoardField.isHoardUpgraded.get(card);
    }
}