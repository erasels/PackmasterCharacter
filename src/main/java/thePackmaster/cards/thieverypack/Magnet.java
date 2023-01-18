package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.StealPowerAction;

import java.util.ArrayList;

public class Magnet extends AbstractThieveryCard {
	public static final String RAW_ID = "Magnet";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	public static final ArrayList<String> stealPowIDs = new ArrayList<String>() {{
		add(ArtifactPower.POWER_ID);
		add(PlatedArmorPower.POWER_ID);
		add(ThornsPower.POWER_ID);
		add(MetallicizePower.POWER_ID);
	}};

	public Magnet() {
		super(ID, COST, TYPE, RARITY, TARGET);
		exhaust = true;
	}

	@Override
	public void triggerOnGlowCheck() {
		glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!m.isDeadOrEscaped() && (m.powers.stream().anyMatch(p -> stealPowIDs.contains(p.ID)))) {
				glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
			}
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (upgraded) {
			addToBot(new StealPowerAction(AbstractDungeon.getMonsters().monsters, stealPowIDs, StealPowerAction.AnimationMode.TO_PLAYER));
		} else {
			addToBot(new StealPowerAction(m, stealPowIDs, StealPowerAction.AnimationMode.TO_PLAYER));
		}
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			target = CardTarget.ALL_ENEMY;
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
