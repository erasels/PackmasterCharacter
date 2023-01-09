package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;

import java.util.ArrayList;

public interface TransmutableCard {
    default ArrayList<AbstractExtraEffectModifier> getMutableAbilities() {
        return new ArrayList<>();
    }

    default void onTransmuted(AbstractCard newCard) {}
}
