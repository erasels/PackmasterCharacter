package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.MindControlAction;

public class MindControl extends AbstractThieveryCard {
	public static final String RAW_ID = "MindControl";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = -1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	private static final int POWER = 12;
	private static final int UPGRADE_BONUS = 3;

	public MindControl() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = POWER;
		exhaust = true;
	}

	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if (!canUse) {
			return false;
		}

		int effect = EnergyPanel.totalCount;
		if (p.hasRelic(ChemicalX.ID)) {
			effect += 2;
		}
		if (m == null) {
			for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
				if (!mo.isDying && !mo.isDead && mo.currentHealth <= magicNumber * effect) {
					return true;
				}
			}
			return false;
		}
		if (m.currentHealth > magicNumber * effect) {
			canUse = false;
			cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
		}

		return canUse;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new MindControlAction(p, m, magicNumber, freeToPlayOnce, energyOnUse, cardStrings.EXTENDED_DESCRIPTION[0]));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
