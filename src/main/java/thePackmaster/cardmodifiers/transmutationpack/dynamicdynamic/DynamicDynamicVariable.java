package thePackmaster.cardmodifiers.transmutationpack.dynamicdynamic;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;

public class DynamicDynamicVariable extends DynamicVariable {
    private static final Color normalColor = Color.valueOf("65ada1");
    private static final Color increasedValueColor = Color.valueOf("9ad6b6");
    private static final Color decreasedValueColor = Color.valueOf("b799bd");
    String key;
    AbstractExtraEffectModifier mod;

    public DynamicDynamicVariable(String key, AbstractExtraEffectModifier mod) {
        this.key = key;
        this.mod = mod;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return mod.isModified(card);
    }

    @Override
    public int value(AbstractCard card) {
        return mod.value(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return mod.baseValue(card);
    }

    @Override
    public Color getNormalColor() {
        return normalColor;
    }

    @Override
    public Color getIncreasedValueColor() {
        return increasedValueColor;
    }

    @Override
    public Color getDecreasedValueColor() {
        return decreasedValueColor;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false; //this is never used
    }
}
