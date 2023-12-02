package thePackmaster.actions.weaponspack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.effects.weaponspack.DamageCurvyEffect;
import thePackmaster.effects.weaponspack.DamageLineEffect;


public class ReapersMarkAction extends AbstractGameAction {

    private AbstractCreature owner;

    protected float offset = MathUtils.random(-180.0F, 180.0F);

    public ReapersMarkAction(AbstractCreature owner) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.owner = owner;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (int i = 0; i < 36; i++) {
                AbstractDungeon.effectList.add(new DamageLineEffect(this.owner.hb.cX, this.owner.hb.cY, new Color(0.901F, 0F, 0F, 1), ((10 * i) + MathUtils.random(-10, 10) + offset)));
                if (i % 2 == 0) {
                    AbstractDungeon.effectList.add(new DamageCurvyEffect(this.owner.hb.cX, this.owner.hb.cY, new Color(0.901F, 0F, 0F, 1)));
                }
            }
            int damage = (this.owner.maxHealth - this.owner.currentHealth) * 4 / 10;
            AbstractDungeon.actionManager.addToTop(new LoseHPAction(this.owner, this.owner, damage, AttackEffect.NONE));
        }

        this.tickDuration();
    }
}
