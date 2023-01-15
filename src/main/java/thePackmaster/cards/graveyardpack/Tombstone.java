package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Tombstone
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("Tombstone");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
  
  private static final int COST = 3;
  
  public Tombstone() {
	super(ID, COST, TYPE, RARITY, TARGET);
   
    this.baseDamage = 30;
    this.damage=this.baseDamage;
    
	this.block = this.baseBlock = 5;
    
    this.exhaust = true;
  }

  
	public void use(AbstractPlayer p, AbstractMonster m) {
		
        AbstractDungeon.player.useJumpAnimation();
        
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
	    if (m != null) {
	    	AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
	    }
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
	}


  
  public void upp() {
      upgradeDamage(9);
      upgradeBlock(3);
  }
}
