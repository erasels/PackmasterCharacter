package thePackmaster.powers.thieverypack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Dictionary;

public class DeceivedPower extends AbstractPackmasterPower {
	public static final String RAW_ID = "DeceivedPower";
	public static final String POWER_ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public DeceivedPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	@Override
	public void onDeath() {
		if (owner.hasPower(PoisonPower.POWER_ID)) {
			AbstractPlayer p = AbstractDungeon.player;

			int gainAmount = amount;
			AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
			if (tmp != null) {
				tmp.flash();
				gainAmount *= 2;
			}

			p.gainGold(gainAmount);
			for (int i = 0; i < gainAmount; ++i) {
				AbstractDungeon.effectList.add(new GainPennyEffect(p, owner.hb.cX, owner.hb.cY, p.hb.cX, p.hb.cY, true));
			}

			// Make sure the enemy does not have this power when it revives
			type = PowerType.DEBUFF;
		}
	}
}
