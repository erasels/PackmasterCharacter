package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.graveyardpack.PlayFromExhaustAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class LastGasp
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("LastGasp");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
  
  private static final int COST = 1;

  
	public LastGasp() {
		super(ID, COST, TYPE, RARITY, TARGET);
		
  		this.isEthereal=true;
	}

  public void use(AbstractPlayer p, AbstractMonster m) {
	  AbstractDungeon.actionManager.addToTop(new PlayFromExhaustAction());
  }

  
	public void upp() {
		this.isEthereal=false;
	}
}
