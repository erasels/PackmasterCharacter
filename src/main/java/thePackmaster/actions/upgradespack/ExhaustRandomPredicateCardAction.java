package thePackmaster.actions.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ExhaustRandomPredicateCardAction extends AbstractGameAction {

    private final Predicate<AbstractCard> predicate;
    private final Consumer<AbstractCard> callback;
    private final CardGroup group;

    public ExhaustRandomPredicateCardAction(Predicate<AbstractCard> predicate, CardGroup group) {
        this(predicate, group, null);
    }

    public ExhaustRandomPredicateCardAction(Predicate<AbstractCard> predicate, CardGroup group, Consumer<AbstractCard> callback) {
        this.predicate = predicate;
        this.group = group;
        this.callback = callback;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> targets = new ArrayList<>();

        for (AbstractCard c : group.group) {
            if (predicate.test(c)) targets.add(c);
        }

        if (targets.size()>0) {
            AbstractCard targetCard = Wiz.getRandomItem(targets);
            if(callback != null)
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        ExhaustRandomPredicateCardAction.this.callback.accept(targetCard);
                        isDone = true;
                    }
                });
            addToTop(new ExhaustSpecificCardAction(targetCard, group));
        }
        isDone = true;
    }
}
