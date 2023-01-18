package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class Smokescreen extends AbstractThieveryCard {
	public static final String RAW_ID = "Smokescreen";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ALL;

	private static final int BLOCK = 5;
	private static final int UPGRADE_BLOCK = 2;
	private static final int HP_LOSS = 5;
	private static final int UPGRADE_HP_LOSS = 2;

	public Smokescreen() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseBlock = BLOCK;
		baseMagicNumber = magicNumber = HP_LOSS;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block, true));

		for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
			if (!mo.isDeadOrEscaped()) {
				addToBot(new GainBlockAction(mo, p, block, true));
			}
		}
		for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
			if (!mo.isDeadOrEscaped()) {
				addToBot(new LoseHPAction(mo, p, magicNumber, AbstractGameAction.AttackEffect.POISON));
			}
		}
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK);
			upgradeMagicNumber(UPGRADE_HP_LOSS);
		}
	}
}
