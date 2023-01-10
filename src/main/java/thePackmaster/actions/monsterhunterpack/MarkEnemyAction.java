package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.monsterhunterpack.HuntersMark;
import thePackmaster.powers.monsterhunterpack.NotHunted;

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
        addToBot(new ApplyPowerAction(target, source, new HuntersMark(target, amount), amount));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (mo != target) {
                if (mo.hasPower(HuntersMark.POWER_ID)) {
                    addToBot(new RemoveSpecificPowerAction(mo, mo, HuntersMark.POWER_ID));
                }
                addToBot(new ApplyPowerAction(mo, source, new NotHunted(mo, amount), amount));
            }
            else {
                if (mo.hasPower(NotHunted.POWER_ID)){
                    addToBot(new RemoveSpecificPowerAction(mo, mo, NotHunted.POWER_ID));
                }
            }
        }
        this.isDone = true;
    }


}
