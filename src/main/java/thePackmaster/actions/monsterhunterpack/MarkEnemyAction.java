package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.powers.monsterhunterpack.HuntersMark;
import thePackmaster.powers.monsterhunterpack.NotHunted;
import thePackmaster.util.Wiz;

public class MarkEnemyAction extends AbstractGameAction {

    int amount;
    AbstractMonster target;
    AbstractCreature source;
    public MarkEnemyAction(AbstractCreature s, AbstractMonster t, int a){
        amount = a;
        target = t;
        source = s;
    }

    @Override
    public void update(){
        if (!target.hasPower(ArtifactPower.POWER_ID)){
            for (AbstractMonster mo : Wiz.getEnemies()) {
                if (mo != target) {
                    if (mo.hasPower(HuntersMark.POWER_ID)) {
                        addToBot(new RemoveSpecificPowerAction(mo, mo, HuntersMark.POWER_ID));
                    }
                    addToBot(new ApplyPowerAction(mo, source, new NotHunted(mo, amount), amount));
                } else if (mo.hasPower(NotHunted.POWER_ID)) {
                        addToBot(new RemoveSpecificPowerAction(mo, mo, NotHunted.POWER_ID));
                    }
                }
            }
        this.isDone = true;
    }


}
