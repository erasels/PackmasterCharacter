package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.StealPowerAction;

public class Magnet extends AbstractThieveryCard {
	public static final String RAW_ID = "Magnet";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

	private static final int POWER = 1;
	private static final int NEW_COST = 0;

	public Magnet() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = POWER;
		exhaust = true;
	}

	@Override
	public void triggerOnGlowCheck() {
		glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!m.isDeadOrEscaped() && (m.hasPower(ArtifactPower.POWER_ID))) {
				glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
			}
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new StealPowerAction(AbstractDungeon.getMonsters().monsters, ArtifactPower.POWER_ID, StealPowerAction.AnimationMode.TO_PLAYER));
		addToBot(new AbstractGameAction() {
			@Override
			public void update() {
				isDone = true;
				if (StealPowerAction.didMiss) {
					addToTop(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
				}
			}
		});
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(NEW_COST);
		}
	}
}
