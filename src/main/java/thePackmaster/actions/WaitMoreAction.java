package thePackmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WaitMoreAction extends AbstractGameAction {
    public WaitMoreAction(float setDur) {
        this.setValues(null, null, 0);
        this.duration = setDur;
        this.actionType = ActionType.WAIT;

        // Prevent them from stacking.
        for(AbstractGameAction actcheck: AbstractDungeon.actionManager.actions) {
            if (actcheck instanceof WaitMoreAction && actcheck != this)
            {
                actcheck.isDone = true;
                duration += 0.15;
            }
        }
    }

    public void update() {
        this.tickDuration();
    }
}
