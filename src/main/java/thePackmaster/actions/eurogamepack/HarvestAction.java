package thePackmaster.actions.eurogamepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.powers.eurogamepack.RoadbuildingPower;
import thePackmaster.powers.eurogamepack.VictoryPoints;

public class HarvestAction extends AbstractGameAction {
    private int damage;

    public HarvestAction(AbstractCreature target, AbstractCreature source, int amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect) {
        this.setValues(target, source, amount);
        this.damage = amount;
        this.actionType = ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        }

        this.tickDuration();
        if (this.isDone) {
            this.TOASTY();
            this.target.damage(new DamageInfo(this.source, this.damage, this.damageType));
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
        }

    }

    private void TOASTY() {
        int tmp = this.damage;
        tmp -= this.target.currentBlock;
        if (tmp > this.target.currentHealth) {
            tmp = this.target.currentHealth;
        }

        if (tmp > 0) {
            int x = tmp;
            if (AbstractDungeon.player.hasPower(RoadbuildingPower.POWER_ID)) {
                x = tmp + AbstractDungeon.player.getPower(RoadbuildingPower.POWER_ID).amount;
            }
            x = x*2;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VictoryPoints(AbstractDungeon.player, x), x));
        }

    }
}
