package thePackmaster.powers.transmutationpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;

import java.util.HashMap;

public interface TransmutableAffectingPower {
    default void onTransmute(TransmuteCardAction action, HashMap<AbstractCard, AbstractCard> pairs) {}

    default void affectTransmutedCard(AbstractCard newCard) {}
}
