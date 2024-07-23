package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static thePackmaster.util.Wiz.atb;

public class DelayedDiceRollAction extends AbstractGameAction {

    private int sides;
    public DelayedDiceRollAction(int sides, int num) {
        this.sides = sides;
        amount = num;
    }
    public void update() {
        atb(new RollAction(sides, amount));
        isDone = true;
    }
}
