package thePackmaster.util.cardvars;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class HoardVar extends DynamicVariable {
    @Override
    public String key() {
        return "hoard";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return HoardField.isHoardModified.get(card);
    }

    public void setIsModified(AbstractCard card, boolean v) {
        HoardField.isHoardModified.set(card, v);
    }

    @Override
    public int value(AbstractCard card) {
        return HoardField.hoard.get(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return HoardField.baseHoard.get(card);
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return HoardField.isHoardUpgraded.get(card);
    }

    @Override
    public Color getIncreasedValueColor() {
        return Settings.RED_TEXT_COLOR;
    }

    @Override
    public Color getDecreasedValueColor() {
        return Settings.GREEN_TEXT_COLOR;
    }
}