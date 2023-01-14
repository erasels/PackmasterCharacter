package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;

public class ThieveryMastery extends AbstractThieveryCard {
	public static final String RAW_ID = "ThieveryMastery";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.POWER;
	private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

	public ThieveryMastery() {
		super(ID, COST, TYPE, RARITY, TARGET);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ThieveryMasteryPower(p, 1)));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			isInnate = true;
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
