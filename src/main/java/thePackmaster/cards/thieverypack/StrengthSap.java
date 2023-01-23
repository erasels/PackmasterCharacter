package thePackmaster.cards.thieverypack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.StealPowerAction;

public class StrengthSap extends AbstractThieveryCard {
	public static final String RAW_ID = "StrengthSap";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 0;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	private static final int WEAK = 1;
	private static final int UPGRADE_WEAK = 1;
	private static final int STEAL_STRENGTH = 1;
	private static final int EXHAUSTIVE = 2;
	private static final int UPGRADE_EXHAUSTIVE = 1;

	public StrengthSap() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = WEAK;
		baseSecondMagic = secondMagic = STEAL_STRENGTH;

		ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);
	}

	@Override
	public void triggerOnGlowCheck() {
		glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!m.isDeadOrEscaped() && hasPositiveStrength(m)) {
				glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
			}
		}
	}

	private boolean hasPositiveStrength(AbstractMonster m) {
		AbstractPower str = m.getPower(StrengthPower.POWER_ID);
		return str != null && str.amount > 0;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
		addToBot(new StealPowerAction(m, StrengthPower.POWER_ID, StealPowerAction.AnimationMode.TO_PLAYER, secondMagic));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_WEAK);
			ExhaustiveVariable.upgrade(this, UPGRADE_EXHAUSTIVE);
		}
	}
}
