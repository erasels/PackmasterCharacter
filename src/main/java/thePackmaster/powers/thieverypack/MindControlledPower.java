package thePackmaster.powers.thieverypack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class MindControlledPower extends AbstractPackmasterPower {
	public static final String RAW_ID = "MindControlledPower";
	public static final String POWER_ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public static Random targetRng;

	public MindControlledPower(AbstractCreature owner) {
		super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, -1);
	}

	@Override
	public void onInitialApplication() {
		owner.flipHorizontal = !owner.flipHorizontal;
		checkForCombatEnd();
	}

	@Override
	public void onRemove() {
		owner.flipHorizontal = !owner.flipHorizontal;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0];
	}

	public static void checkForCombatEnd() {
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			if (!m.isDying && !m.isEscaping && m.currentHealth > 0 && !m.hasPower(POWER_ID)) {
				return;
			}
		}

		// All living enemies are Mind Controlled. They should die.
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			AbstractPower pow = m.getPower(POWER_ID);
			if (pow != null) {
				AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
					// Revived enemies will not be mind controlled.
					@Override
					public void update() {
						if (m.powers.contains(pow)) {
							pow.onRemove();
							m.powers.remove(pow);
						}
						isDone = true;
					}
				});
				AbstractDungeon.actionManager.addToTop(new InstantKillAction(m));
			}
		}
	}
}
