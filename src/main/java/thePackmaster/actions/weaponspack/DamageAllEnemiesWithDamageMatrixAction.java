package thePackmaster.actions.weaponspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class DamageAllEnemiesWithDamageMatrixAction extends AbstractGameAction {
    private int damage;

    public DamageAllEnemiesWithDamageMatrixAction(int damage) {
        this.damage = damage;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AttackEffect.FIRE));
        this.isDone = true;
    }
}
