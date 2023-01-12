package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class FleshLikeOak extends AbstractGraveyardCard
{
  public static final String ID = makeID("FleshLikeOak");
  
  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
  
  private static final int COST = 2;
  
	public FleshLikeOak() {
		super(ID, COST, TYPE, RARITY, TARGET);
		
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = 1;
		this.block = this.baseBlock = 12;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));

		AbstractDungeon.actionManager
				.addToBottom(new GainBlockAction(p, p, this.block));
		
	}

  
	public void upp() {
		upgradeBlock(4);
	}
}
