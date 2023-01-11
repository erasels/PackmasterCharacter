package thePackmaster.actions.psychicpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SuperFastDamageAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean muteSfx;

    public SuperFastDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean muteSfx) {
        this.muteSfx = muteSfx;
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public SuperFastDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
        this(target, info, effect, false);
    }

    public SuperFastDamageAction(AbstractCreature target, DamageInfo info) {
        this(target, info, AttackEffect.NONE);
    }

    public void update() {
        if (this.shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
            this.isDone = true;
        }
        else
        {
            if (this.info.type != DamageInfo.DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));

            if (this.attackEffect == AttackEffect.POISON) {
                this.target.tint.color = Color.CHARTREUSE.cpy();
                this.target.tint.changeColor(Color.WHITE.cpy());
            } else if (this.attackEffect == AttackEffect.FIRE) {
                this.target.tint.color = Color.RED.cpy();
                this.target.tint.changeColor(Color.WHITE.cpy());
            }

            this.target.damage(this.info);
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            this.isDone = true;
        }
    }
}