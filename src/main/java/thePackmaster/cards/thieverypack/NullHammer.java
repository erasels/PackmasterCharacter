package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.StealPowerAction;

import java.util.ArrayList;

public class NullHammer extends AbstractThieveryCard {
	public static final String RAW_ID = "NullHammer";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 2;
	private static final AbstractCard.CardType TYPE = CardType.ATTACK;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	private static final int POWER = 15;
	private static final int UPGRADE_BONUS = 5;

	public static final ArrayList<String> stealPowIDs = new ArrayList<String>() {{
		add(PlatedArmorPower.POWER_ID);
		add(ThornsPower.POWER_ID);
		add(MetallicizePower.POWER_ID);
	}};

	@Override
	public void triggerOnGlowCheck() {
		glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!m.isDeadOrEscaped() && (m.powers.stream().anyMatch(p -> stealPowIDs.contains(p.ID)))) {
				glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
			}
		}
	}

	public NullHammer() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		addToBot(new StealPowerAction(m, stealPowIDs, StealPowerAction.AnimationMode.HAMMERED));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
