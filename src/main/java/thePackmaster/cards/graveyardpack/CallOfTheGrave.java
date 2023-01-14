package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import thePackmaster.powers.graveyardpack.CallOfTheGravePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CallOfTheGrave
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("CallOfTheGrave");
  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

  private static final int COST = 1;
  
  public CallOfTheGrave() {
	  super(ID, COST, TYPE, RARITY, TARGET);
  }

  
  public void upp() {
      this.isInnate = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m) {
	 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CallOfTheGravePower(p), 1));
  }
}
