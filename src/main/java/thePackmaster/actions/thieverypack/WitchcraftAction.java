package thePackmaster.actions.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.potions.thieverypack.DivinePotion;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;

public class WitchcraftAction extends AbstractGameAction {
	public WitchcraftAction(AbstractCreature target, AbstractCreature source, int amount) {
		setValues(target, source, amount);
		actionType = ActionType.DAMAGE;
		startDuration = duration = DEFAULT_DURATION;
	}

	public void update() {
		if (duration == startDuration) {
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.NONE));
		}

		tickDuration();
		if (isDone) {
			target.damage(new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS));
			if (target.lastDamageTaken > 0) {
				int healAmount = target.lastDamageTaken;
				AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
				if (tmp != null) {
					tmp.flash();
					healAmount *= 2;
				}

				addToTop(new HealAction(source, source, healAmount));
				addToTop(new WaitAction(0.1F));
			}
			if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower(MinionPower.POWER_ID)) {
				addToBot(new ObtainPotionAction(new DivinePotion()));
			}

			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
	}
}
