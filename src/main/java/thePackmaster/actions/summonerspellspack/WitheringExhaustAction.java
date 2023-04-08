package thePackmaster.actions.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import thePackmaster.powers.summonerspellspack.WitheringExhaustPower;

public class WitheringExhaustAction extends AbstractGameAction {

    public WitheringExhaustAction(AbstractCreature target, AbstractCreature source) {
        this.source = source;
        this.target = target;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new WitheringExhaustPower(target), 1));
        this.isDone = true;
    }
}