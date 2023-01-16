package thePackmaster.powers.thieverypack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class CunningPower extends AbstractPackmasterPower implements OnReceivePowerPower {
	public static final String RAW_ID = "CunningPower";
	public static final String POWER_ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public CunningPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + (amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
	}

	@Override
	public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		if (power.type == PowerType.BUFF && !power.ID.equals(POWER_ID)) {
			flashWithoutSound();
			addToBot(new DrawCardAction(amount));
		}
		return true;
	}

	@Override
	public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
		return stackAmount;
	}
}
