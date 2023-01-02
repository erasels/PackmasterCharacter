package thePackmaster.actions.summonspack;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class FastTempHPAction extends AbstractGameAction {
    public FastTempHPAction(AbstractCreature target, AbstractCreature source, int amount) {
        setValues(target, source, amount);
        actionType = AbstractGameAction.ActionType.HEAL;
    }

    public void update() {
        TempHPField.tempHp.set(target, (Integer)TempHPField.tempHp.get(target) + amount);
        if (amount > 0) {
            AbstractDungeon.effectsQueue.add(new HealEffect(target.hb.cX - target.animX, target.hb.cY, amount));
            target.healthBarUpdatedEvent();
        }

        isDone = true;
    }
}
