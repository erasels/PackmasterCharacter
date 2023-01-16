package thePackmaster.actions.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SheddingAction extends AbstractGameAction {

    public SheddingAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> toRandomize = new ArrayList<>();
        ArrayList<AbstractCard> tempHand = new ArrayList<>();
        tempHand.addAll(AbstractDungeon.player.hand.group);
        while (toRandomize.size() < amount && tempHand.size() > 0) {

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
            while (maxCostCards.size() > 0) {
                int r = AbstractDungeon.cardRandomRng.random(maxCostCards.size() -1);
                AbstractCard card = maxCostCards.get(r);
                toRandomize.add(card);
                tempHand.remove(card);
                maxCostCards.remove(card);
            }
        }
        if (toRandomize.size() > 0) {
            addToTop(new RandomizeCostAction(toRandomize.toArray(new AbstractCard[0])));
        }
        isDone = true;
    }
}
