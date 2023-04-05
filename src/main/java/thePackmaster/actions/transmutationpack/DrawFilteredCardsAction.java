package thePackmaster.actions.transmutationpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.function.Predicate;

import static thePackmaster.util.Wiz.p;

public class DrawFilteredCardsAction extends AbstractGameAction {
    private final Predicate<AbstractCard> filter;

    public DrawFilteredCardsAction(int amount, Predicate<AbstractCard> filter) {
        setValues(null, null, amount);
        this.filter = filter;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> foundInDraw = new ArrayList<>();
        ArrayList<AbstractCard> foundInDiscard = new ArrayList<>();
        for (int i = p().drawPile.group.size() - 1; i >= 0; --i) {
            AbstractCard card = p().drawPile.group.get(i);
            if (filter.test(card)) {
                foundInDraw.add(card);
                if (foundInDraw.size() >= amount) {
                    break;
                }
            }
        }
        if (foundInDraw.size() < amount) {
            for (int i = p().discardPile.group.size() - 1; i >= 0; --i) {
                AbstractCard card = p().discardPile.group.get(i);
                if (filter.test(card)) {
                    foundInDiscard.add(card);
                    if (foundInDraw.size() + foundInDiscard.size() >= amount) {
                        break;
                    }
                }
            }
        }
        for (AbstractCard card : foundInDraw) {
            p().drawPile.group.remove(card);
            p().drawPile.addToTop(card);
        }
        for (AbstractCard card : foundInDiscard) {
            p().discardPile.removeCard(card);
            p().discardPile.moveToDeck(card, false);
        }
        if (foundInDraw.size() != 0 || foundInDiscard.size() != 0) {
            addToTop(new DrawCardAction(foundInDraw.size() + foundInDiscard.size()));
        }
        isDone = true;
    }
}
