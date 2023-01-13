package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FuneraryJewels
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("FuneraryJewels");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
  

  private static final int COST = 1;
  
	public FuneraryJewels() {
		super(ID, COST, TYPE, RARITY, TARGET);
		
		this.exhaust = true;
		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
		this.baseBlock = 3;
		this.block = this.baseBlock;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
	    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
	}


  
	public void upp() {
		upgradeBlock(3);
	}
}
