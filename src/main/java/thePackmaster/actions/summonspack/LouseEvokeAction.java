package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Louse;
import thePackmaster.vfx.summonspack.LouseExitEffect;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class LouseEvokeAction extends AbstractGameAction {
    private static final float DURATION = 1.0F;
    private float thunkTiming;
    private boolean thunkEffect;
    public Louse louse;
    private float targetX;
    private float targetY;
    private float sourceX;
    private float sourceY;
    public Louse original;

    public LouseEvokeAction(Louse louse, Louse original) {
        this.original = original;
        this.louse = louse;
        amount = louse.passiveAmount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;
    }

    public void update() {
        if (duration == DURATION) {
            target = AbstractDungeon.getRandomMonster();
            if (target == null) {
                isDone = true;
                return;
            } else
                amount = Louse.applyLockOn(target, amount);
            targetX = target.hb.cX;
            targetY = target.hb.cY;
            sourceX = original.cX;
            sourceY = original.cY;
            if (!SpireAnniversary5Mod.louseList.contains(louse))
                SpireAnniversary5Mod.louseList.add(louse);
            thunkTiming = (targetX - sourceX)/3200.0f;
            if (thunkTiming > 0.25f)
                thunkTiming = 0.25f;
            if (thunkTiming < 0.1f)
                thunkTiming = 0.1f;

            louse.startShoot();
            targetX = target.hb.cX + AbstractDungeon.miscRng.random(-25.0f*Settings.xScale, 25.0f*Settings.xScale);
            targetY = target.hb.cY + AbstractDungeon.miscRng.random(-25.0f*Settings.yScale, 25.0f*Settings.yScale);
            sourceX = louse.cX;
            sourceY = louse.cY;
        }

        louse.cX = sourceX + (targetX - sourceX)*(DURATION - duration)/ thunkTiming;
        louse.cY = sourceY + (targetY - sourceY)*(DURATION - duration)/ thunkTiming;

        if (duration <= DURATION - thunkTiming && !thunkEffect) {
            thunkEffect = true;
            louse.cX = targetX;
            louse.cY = targetY;
            if (target != null && target.currentHealth > 0 && adp() != null) {
                DamageInfo info = new DamageInfo(adp(), louse.passiveAmount, DamageInfo.DamageType.THORNS);
                att(new DamageAction(target, info, AttackEffect.BLUNT_HEAVY, true));
            }
            AbstractDungeon.effectList.add(new LouseExitEffect(louse));
            isDone = true;
        }

        tickDuration();
    }
}
