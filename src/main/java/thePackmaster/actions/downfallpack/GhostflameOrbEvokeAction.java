package thePackmaster.actions.downfallpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import thePackmaster.actions.metapack.WaitMoreAction;

public class GhostflameOrbEvokeAction extends AbstractGameAction {
    private final DamageInfo info;

    public GhostflameOrbEvokeAction(DamageInfo info) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
    }

    public void update() {
        this.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.info.base, true, true), DamageType.THORNS, AttackEffect.FIRE));

        boolean waited = false;
        for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
            if (!waited) {
                waited = true;
                addToTop(new WaitMoreAction(Settings.FAST_MODE ? 0.36f : 0.5f));
            }
            if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                addToTop(new VFXAction(new FireballEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, m3.hb.cX, m3.hb.cY), 0.0F));
            }
        }

        this.isDone = true;
    }
}
