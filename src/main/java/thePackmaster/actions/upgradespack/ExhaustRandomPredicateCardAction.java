package thePackmaster.actions.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ExhaustRandomPredicateCardAction extends AbstractGameAction {

    private final Predicate<AbstractCard> predicate;
    private final CardGroup group;

    public ExhaustRandomPredicateCardAction(Predicate<AbstractCard> predicate, CardGroup group) {
        this.predicate = predicate;
        this.group = group;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> targets = new ArrayList<>();

        for (AbstractCard c : group.group) {
            if (predicate.test(c)) targets.add(c);
        }

        if (targets.size()>0) {
            int r = AbstractDungeon.cardRandomRng.random(targets.size()-1);
            addToBot(new ExhaustSpecificCardAction(targets.get(r), group));
        }
        isDone = true;
    }
}
