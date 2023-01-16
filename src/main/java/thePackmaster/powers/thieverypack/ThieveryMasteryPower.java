package thePackmaster.powers.thieverypack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class ThieveryMasteryPower extends AbstractPackmasterPower {
	public static final String RAW_ID = "ThieveryMasteryPower";
	public static final String POWER_ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public ThieveryMasteryPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}
}
