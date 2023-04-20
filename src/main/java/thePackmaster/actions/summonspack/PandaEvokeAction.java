package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Panda;
import thePackmaster.vfx.summonspack.PandaExitEffect;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class PandaEvokeAction extends AbstractGameAction {
    private static final float DURATION = 1.0F;
    private float thunkTiming;
    private boolean thunkEffect;
    public Panda panda;
    private float targetX;
    private float targetY;
    private float sourceX;
    private float sourceY;
    public Panda original;

    public PandaEvokeAction(Panda panda, Panda original) {
        this.original = original;
        this.panda = panda;
        amount = panda.passiveAmount;
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
                amount = Panda.applyLockOn(target, amount);
            targetX = target.hb.cX;
            targetY = target.hb.cY;
            sourceX = original.cX;
            sourceY = original.cY;
            if (!SpireAnniversary5Mod.pandaList.contains(panda))
                SpireAnniversary5Mod.pandaList.add(panda);
            thunkTiming = (targetX - sourceX)/3200.0f/Settings.scale;
            if (thunkTiming > 0.25f)
                thunkTiming = 0.25f;
            if (thunkTiming < 0.1f)
                thunkTiming = 0.1f;

            panda.startShoot();
            targetX = target.hb.cX + AbstractDungeon.miscRng.random(-25.0f*Settings.xScale, 25.0f*Settings.xScale);
            targetY = target.hb.cY + AbstractDungeon.miscRng.random(-25.0f*Settings.yScale, 25.0f*Settings.yScale);
            sourceX = panda.cX;
            sourceY = panda.cY;
        }

        panda.cX = sourceX + (targetX - sourceX)*(DURATION - duration)/ thunkTiming;
        panda.cY = sourceY + (targetY - sourceY)*(DURATION - duration)/ thunkTiming;

        if (duration <= DURATION - thunkTiming && !thunkEffect) {
            thunkEffect = true;
            panda.cX = targetX;
            panda.cY = targetY;
            if (target != null && target.currentHealth > 0 && adp() != null) {
                DamageInfo info = new DamageInfo(adp(), Panda.applyLockOn(target, panda.evokeAmount), DamageInfo.DamageType.THORNS);
                att(new DamageAction(target, info, AttackEffect.BLUNT_HEAVY, true));
            }
            AbstractDungeon.effectList.add(new PandaExitEffect(panda));
            isDone = true;
        }

        tickDuration();
    }
}
