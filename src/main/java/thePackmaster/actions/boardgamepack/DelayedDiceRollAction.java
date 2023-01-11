package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import thePackmaster.powers.boardgamepack.DicePower;

import static thePackmaster.util.Wiz.adp;

public class DelayedDiceRollAction extends AbstractGameAction {

    private int sides;
    public DelayedDiceRollAction(int sides, int num) {
        this.sides = sides;
        amount = num;
    }
    public void update() {

        for (int i = 0; i < amount; i++) {
            addToBot(new ApplyPowerAction(adp(), adp(), new DicePower(adp(), sides), sides));
        }
        isDone = true;
    }
}
