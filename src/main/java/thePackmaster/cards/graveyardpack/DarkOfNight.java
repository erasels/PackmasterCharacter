package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.utility.SFXAction;

import thePackmaster.powers.graveyardpack.DarkOfNightPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DarkOfNight
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("DarkOfNight");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
  

  private static final int COST = 0;

  
	public DarkOfNight() {
		super(ID, COST, TYPE, RARITY, TARGET);
		
		this.baseMagicNumber=1;
		this.magicNumber=this.baseMagicNumber;
	}

  public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DarkOfNightPower(p,this.magicNumber), this.magicNumber));
      AbstractDungeon.actionManager.addToBottom(new SFXAction("EVENT_SPIRITS"));
  }

  
  public void upp() {
    upgradeMagicNumber(1);
  }
}
