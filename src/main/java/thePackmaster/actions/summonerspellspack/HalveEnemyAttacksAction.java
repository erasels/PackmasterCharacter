package thePackmaster.actions.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.WitheringExhaustPower;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HalveEnemyAttacksAction extends AbstractGameAction {

    public HalveEnemyAttacksAction(AbstractCreature target, int timesToHalve) {
        this.target = target;
        this.actionType = ActionType.SPECIAL;
        this.amount = timesToHalve;
    }

    public void update() {
        List<AbstractGameAction> attackActions = new ArrayList<>();

        int initialAttackCount = 1; // start at 1, as first attack always triggers and will not be found here

        for (Iterator<AbstractGameAction> i = AbstractDungeon.actionManager.actions.iterator(); i.hasNext(); ) {
            AbstractGameAction a = i.next();

            if (a.source == target && a instanceof DamageAction) {
                attackActions.add(a);
                initialAttackCount++;
            }
        }

        int newAttackCount = (int)Math.ceil((double)initialAttackCount * Math.pow(0.5, amount)); // halves as many times as needed, then rounds up
        int attacksToRemove = initialAttackCount - newAttackCount;

        if (attacksToRemove > attackActions.size())
            SpireAnniversary5Mod.logger.info("HalveEnemyAttacksAction is trying to remove more attacks than exist! Cancelling removal.");
        else
            for (int i = 0; i < attacksToRemove; i++)
                AbstractDungeon.actionManager.actions.remove(attackActions.get(i));

        this.isDone = true;
    }
}