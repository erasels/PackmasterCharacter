package thePackmaster.actions.basics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;

import java.util.ArrayList;
import java.util.function.Predicate;

import static thePackmaster.util.Wiz.p;

public class DrawFilteredCardsAndUpgradeAction extends AbstractGameAction {
    private final Predicate<AbstractCard> filter;
    private final boolean force;
    private final int times;

    public DrawFilteredCardsAndUpgradeAction(int amount, Predicate<AbstractCard> filter, boolean force, int times) {
        setValues(null, null, amount);
        this.filter = filter;
        this.force = force;
        this.times = times;
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
            p().drawPile.removeCard(card);
            p().drawPile.moveToDeck(card, false);
        }
        for (AbstractCard card : foundInDiscard) {
            p().discardPile.removeCard(card);
            p().discardPile.moveToDeck(card, false);
        }
        if (foundInDraw.size() != 0 || foundInDiscard.size() != 0) {
            addToTop(new DrawCardAction(foundInDraw.size() + foundInDiscard.size()));
        }
        if(this.force) {
            addToBot(new SuperUpgradeAction(foundInDraw, times));
            addToBot(new SuperUpgradeAction(foundInDiscard, times));
        } else {
            for (AbstractCard card : foundInDraw) {
                addToBot(new UpgradeSpecificCardAction(card));
            }
            for (AbstractCard card : foundInDiscard) {
                addToBot(new UpgradeSpecificCardAction(card));
            }
        }
        isDone = true;
    }
}
