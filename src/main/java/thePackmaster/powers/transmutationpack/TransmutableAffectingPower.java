package thePackmaster.powers.transmutationpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;

public interface TransmutableAffectingPower {
    default void onTransmute(TransmuteCardAction action) {}

    default void affectTransmutedCard(AbstractCard newCard) {}
}
