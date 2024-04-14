package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.thieverypack.DeceivedPower;

public class CunningPoison extends AbstractThieveryCard {
	public static final String RAW_ID = "CunningPoison";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

	private static final int POISON = 4;
	private static final int UPGRADE_POISON = 2;
	private static final int GOLD = 12;
	private static final int UPGRADE_GOLD = 3;

	public CunningPoison() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = POISON;
		baseSecondMagic = secondMagic = GOLD;
		exhaust = true;
		tags.add(CardTags.HEALING);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractCard card = this;
		addToBot(new AbstractGameAction() {
			@Override
			public void update() {
				AbstractMonster strongestMonster = null;
				for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
					if (!m.isDeadOrEscaped()) {
						if (strongestMonster == null) {
							strongestMonster = m;
						} else if (m.currentHealth > strongestMonster.currentHealth) {
							strongestMonster = m;
						}
					}
				}
				if (strongestMonster != null) {
					addToTop(new ApplyPowerAction(strongestMonster, p, new DeceivedPower(strongestMonster, secondMagic)));
					addToTop(new ApplyPowerAction(strongestMonster, p, new PoisonPower(strongestMonster, p, magicNumber), magicNumber, AbstractGameAction.AttackEffect.POISON));
					addToTop(new VFXAction(new PotionBounceEffect(p.hb.cX, p.hb.cY, strongestMonster.hb.cX, card.hb.cY), 0.5F));
				}
				isDone = true;
			}
		});
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_POISON);
			upgradeSecondMagic(UPGRADE_GOLD);
		}
	}
}
