package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GremlinLanceFollowupAction extends AbstractGameAction {

    AbstractMonster t;
    public GremlinLanceFollowupAction(AbstractMonster target){
        t = target;
    }

    @Override
    public void update() {
        if (t.isDead || t.isDying || t.halfDead) {
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(1));
        }
        this.isDone = true;
    }
}
