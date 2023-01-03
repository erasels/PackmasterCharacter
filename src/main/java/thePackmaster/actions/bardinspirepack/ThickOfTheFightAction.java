package thePackmaster.actions.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ThickOfTheFightAction extends AbstractGameAction
{
    private static final float DURATION = 0.01f;
    private static final float POST_ATTACK_WAIT_DUR = 0.2f;

    private DamageInfo info;
    private int numTimes;

    public ThickOfTheFightAction(AbstractCreature target, DamageInfo info, int numTimes)
    {
        this.target = target;
        this.info = info;
        this.numTimes = numTimes;
        actionType = ActionType.DAMAGE;
        attackEffect = AttackEffect.SLASH_HORIZONTAL;
        duration = DURATION;
    }

    @Override
    public void update()
    {
        if (target == null) {
            isDone = true;
            return;
        }
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            isDone = true;
            return;
        }

        if (target.currentHealth > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
            info.applyPowers(info.owner, target);
            target.damage(info);
            if (numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                --numTimes;
                AbstractDungeon.actionManager.addToTop(new ThickOfTheFightAction(AbstractDungeon.getMonsters().getRandomMonster(true), info, numTimes));
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(POST_ATTACK_WAIT_DUR));
        }
        isDone = true;
    }
}
