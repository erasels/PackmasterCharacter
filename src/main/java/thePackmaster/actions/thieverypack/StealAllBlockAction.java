package thePackmaster.actions.thieverypack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;

public class StealAllBlockAction extends AbstractGameAction {
	public float t;

	public static AbstractCreature affectedCreature = null;

	public static StealAllBlockAction activatedInstance = null;

	public StealAllBlockAction(AbstractCreature target, AbstractCreature source) {
		setValues(target, source);
		actionType = ActionType.BLOCK;
		startDuration = duration = Settings.ACTION_DUR_MED;
	}

	public void calcPosition(float[] x, float[] y) {
		float targetX = AbstractDungeon.player.hb.cX - AbstractDungeon.player.hb.width / 2.0F;
		float targetY = AbstractDungeon.player.hb.cY - AbstractDungeon.player.hb.height / 2.0F;
		x[0] = MathUtils.lerp(x[0], targetX, t);
		y[0] = MathUtils.lerp(y[0], targetY, t);
	}

	public void update() {
		if (duration == startDuration) {
			if (!target.isDying && !target.isDead && duration == startDuration && target.currentBlock > 0) {
				activatedInstance = this;
				affectedCreature = target;

				t = 0;
			} else {
				isDone = true;
				activatedInstance = null;
				return;
			}
		}

		tickDuration();

		if (isDone) {
			t = 1;
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(source.hb.cX, source.hb.cY, AttackEffect.SHIELD));
			int amount = target.currentBlock;
			AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
			if (tmp != null) {
				tmp.flash();
				amount *= tmp.amount + 1;
			}
			source.addBlock(amount);
			target.loseBlock();

			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				c.applyPowers();
			}
			activatedInstance = null;
		} else {
			t = (startDuration - duration) / startDuration;
		}
	}
}
