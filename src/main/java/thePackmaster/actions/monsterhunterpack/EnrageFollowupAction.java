package thePackmaster.actions.monsterhunterpack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.monsterhunterpack.EnragePowerPlayer;

public class EnrageFollowupAction extends AbstractGameAction {

    int amount;
    AbstractMonster target;
    AbstractCreature source;
    public EnrageFollowupAction(AbstractCreature s, AbstractMonster t, int a){
        amount = a;
        target = t;
        source = s;
    }

    @Override
    public void update(){
        if (!target.isDead && !target.isDying && !target.isEscaping && !target.halfDead){
            int roll = MathUtils.random(2);
            if (roll == 0) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1A"));
            } else if (roll == 1) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1B"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1C"));
            }
            addToBot(new ApplyPowerAction(source, source, new EnragePowerPlayer(source, amount), amount));
        }
        this.isDone = true;
    }


}
