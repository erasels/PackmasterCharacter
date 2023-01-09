package thePackmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import thePackmaster.util.Wiz;

public class DelayActionAction extends AbstractGameAction {
    private AbstractGameAction action;

    public DelayActionAction(AbstractGameAction action) {
        this.action = action;
    }

    @Override
    public void update() {
        Wiz.atb(action);
        isDone = true;
    }
}
