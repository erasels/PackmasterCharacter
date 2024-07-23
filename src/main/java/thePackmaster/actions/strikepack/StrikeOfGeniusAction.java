package thePackmaster.actions.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.util.Wiz;

public class StrikeOfGeniusAction extends AbstractGameAction {

    private AbstractCreature target;

    public StrikeOfGeniusAction(AbstractCreature target) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.target = target;
    }

    public void update() {

        this.isDone = true;
        if (target.lastDamageTaken > 0) {
            this.addToTop(new GainBlockAction(Wiz.p(), target.lastDamageTaken));
        }

    }


}