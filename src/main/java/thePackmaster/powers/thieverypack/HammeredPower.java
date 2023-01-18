package thePackmaster.powers.thieverypack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.StealPowerAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.HashMap;

public class HammeredPower extends AbstractPackmasterPower implements OnReceivePowerPower {
	public static final String RAW_ID = "HammeredPower";
	public static final String POWER_ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public HashMap<String, Integer> powerIDAmountMap = new HashMap<>();

	AbstractMonster mOwner;

	public HammeredPower(AbstractMonster owner) {
		super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, -1);
		mOwner = owner;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0];
	}

	@Override
	public void atEndOfRound() {
		addToBot(new RemoveSpecificPowerAction(owner, owner, this));
	}

	@Override
	public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if (power.type == AbstractPower.PowerType.BUFF) {
			flashWithoutSound();
			if (powerIDAmountMap.containsKey(power.ID)) {
				powerIDAmountMap.put(power.ID, power.amount + powerIDAmountMap.get(power.ID));
			} else {
				powerIDAmountMap.put(power.ID, power.amount);
			}
			addToBot(new AbstractGameAction() {
				@Override
				public void update() {
					if (!powerIDAmountMap.isEmpty()) {
						addToTop(new StealPowerAction(mOwner, new HashMap<>(powerIDAmountMap), StealPowerAction.AnimationMode.TO_PLAYER));
					}
					powerIDAmountMap.clear();
					isDone = true;
				}
			});
		}
		return true;
	}

	@Override
	public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
		return stackAmount;
	}
}
