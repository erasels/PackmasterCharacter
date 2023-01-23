package thePackmaster.cardmodifiers.transmutationpack.dynamicdynamic;

import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class DynamicDynamicVariable extends DynamicVariable {
    String key;
    DynamicProvider provider;

    public DynamicDynamicVariable(String key, DynamicProvider mod) {
        this.key = key;
        this.provider = mod;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return provider.isModified(card);
    }

    @Override
    public int value(AbstractCard card) {
        return provider.value(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return provider.baseValue(card);
    }

    @Override
    public Color getNormalColor() {
        Color color = provider.getNormalColor();
        return color != null ? color : super.getNormalColor();
    }

    @Override
    public Color getIncreasedValueColor() {
        Color color = provider.getIncreasedValueColor();
        return color != null ? color : super.getIncreasedValueColor();
    }

    @Override
    public Color getDecreasedValueColor() {
        Color color = provider.getDecreasedValueColor();
        return color != null ? color : super.getDecreasedValueColor();
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false; //this is never used
    }
}
