package thePackmaster.cards.thieverypack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.thieverypack.WitchcraftAction;
import thePackmaster.vfx.bardinspirepack.LifeDrainEffect;

public class Witchcraft extends AbstractThieveryCard {
	public static final String RAW_ID = "Witchcraft";
	public static final String ID = SpireAnniversary5Mod.makeID(RAW_ID);
	private static final int COST = 1;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

	private static final int POWER = 4;
	private static final int UPGRADE_BONUS = 2;

	public Witchcraft() {
		super(ID, COST, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = POWER;
		exhaust = true;
		tags.add(CardTags.HEALING);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			// Using bard's vfx here is intended... for now.
			addToBot(new VFXAction(new LifeDrainEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.5f));
		}
		addToBot(new WitchcraftAction(m, p, magicNumber));
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
