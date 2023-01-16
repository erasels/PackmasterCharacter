package thePackmaster.potions.thieverypack;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import thePackmaster.SpireAnniversary5Mod;

public class DivinePotion extends CustomPotion {
	public static final String POTION_ID = SpireAnniversary5Mod.makeID("DivinePotion");
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

	public static final String NAME = potionStrings.NAME;
	public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

	public DivinePotion() {
		super(NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.HEART, PotionColor.FIRE);
		isThrown = false;
		targetRequired = false;
	}

	@Override
	public void initializeData() {
		potency = this.getPotency();
		description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
		tips.clear();
		tips.add(new PowerTip(name, description));
		tips.add(new PowerTip(
			BaseMod.getKeywordTitle("temporary_hp"),
			BaseMod.getKeywordDescription("temporary_hp"))
		);
	}

	@Override
	public void use(AbstractCreature target) {
		addToBot(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, potency));
	}

	@Override
	public int getPotency(int ascensionLevel) {
		return 8;
	}

	@Override
	public AbstractPotion makeCopy() {
		return new DivinePotion();
	}
}
