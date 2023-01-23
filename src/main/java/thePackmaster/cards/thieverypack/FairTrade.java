package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;

public class FairTrade extends AbstractThieveryCard {
	public static final String RAW_ID = "FairTrade";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.ATTACK;
	private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	private static final int POWER = 6;
	private static final int UPGRADE_BONUS = 2;
	private static final int STRENGTH = 1;

	public FairTrade() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseDamage = POWER;
		baseMagicNumber = magicNumber = STRENGTH;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
		addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, magicNumber)));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
