package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.thieverypack.CunningPower;

public class Cunning extends AbstractThieveryCard {
	public static final String RAW_ID = "Cunning";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 2;
	private static final AbstractCard.CardType TYPE = CardType.POWER;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

	private static final int NEW_COST = 1;

	public Cunning() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = 1;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new CunningPower(p, magicNumber)));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(NEW_COST);
		}
	}
}
