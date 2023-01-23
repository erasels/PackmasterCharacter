package thePackmaster.util.dynamicdynamic;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.UUID;

public interface DynamicProvider {

    UUID getDynamicUUID();

    boolean isModified(AbstractCard card);

    int value(AbstractCard card);

    int baseValue(AbstractCard card);

    default Color getNormalColor() {
        return null;
    }

    default Color getIncreasedValueColor() {
        return null;
    }

    default Color getDecreasedValueColor() {
        return null;
    }
}
