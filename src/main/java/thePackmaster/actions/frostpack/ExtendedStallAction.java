package thePackmaster.actions.frostpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;

import java.util.ArrayList;

public class ExtendedStallAction extends AbstractGameAction {

    public ExtendedStallAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> toFreeze = new ArrayList<>();
        ArrayList<AbstractCard> tempHand = new ArrayList<>();
        tempHand.addAll(AbstractDungeon.player.hand.group);
        while (toFreeze.size() < amount && tempHand.size() > 0) {

            //identify highest cost cards
            int maxCost = -1;
            ArrayList<AbstractCard> maxCostCards = new ArrayList<>();
            for (AbstractCard c : tempHand) {
                if (c.costForTurn > maxCost) {
                    maxCost = c.costForTurn;
                    maxCostCards.clear();
                    maxCostCards.add(c);
                } else if (c.costForTurn == maxCost) {
                    maxCostCards.add(c);
                }
            }

            //Stop if no randomizable cards are left
            if (maxCost < 0) break;

            //choose which are randomized, remove them from tempHand
            while (maxCostCards.size() > 0 && toFreeze.size() < amount) {
                int r = AbstractDungeon.cardRandomRng.random(maxCostCards.size() -1);
                AbstractCard card = maxCostCards.get(r);
                toFreeze.add(card);
                tempHand.remove(card);
                maxCostCards.remove(card);
            }
        }
        if (toFreeze.size() > 0) {
            addToTop(new SimpleAddModifierAction(new FrozenMod(), toFreeze.get(0)));
        }
        isDone = true;
    }
}
