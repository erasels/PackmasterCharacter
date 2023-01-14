package thePackmaster.actions.sneckopack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class RandomizeCostAction extends AbstractGameAction {

    private AbstractCard[] cards;

    public RandomizeCostAction(AbstractCard ... cards) {
        this.cards = cards;
    }

    public RandomizeCostAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (cards == null) {
            ArrayList<AbstractCard> applicable = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cost >= 0) {
                    applicable.add(c);
                }
            }
            int randomisations = Math.min(applicable.size(),amount);
            cards = new AbstractCard[randomisations];
            for (int i = 0 ; i < randomisations ; i++) {
                int r = AbstractDungeon.cardRandomRng.random(applicable.size() - 1);
                cards[i] = applicable.get(r);
                applicable.remove(r);
            }
        }

        for (AbstractCard card : cards) {
            if (card.cost >= 0) {// 24
                int newCost = AbstractDungeon.cardRandomRng.random(3);
                if (card.cost != newCost) {
                    card.cost = newCost;
                    card.costForTurn = card.cost;
                    card.isCostModified = true;
                }
                card.superFlash(Color.LIME.cpy());
            }
        }
        isDone = true;
    }
}
