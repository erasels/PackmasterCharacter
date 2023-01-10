package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhipFollowUpAction extends AbstractGameAction {

    AbstractMonster t;
    AbstractCard toReturn;
    public WhipFollowUpAction(AbstractMonster target, AbstractCard whip){
        t = target;
        toReturn = whip;
    }

    @Override
    public void update() {
        if (t.isDead || t.isDying || t.halfDead) {
            toReturn.setCostForTurn(0);
            toReturn.returnToHand = true;
        }
        else {
            toReturn.returnToHand = false;
        }
        this.isDone = true;
    }
}
