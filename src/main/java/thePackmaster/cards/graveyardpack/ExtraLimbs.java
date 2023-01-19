package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class ExtraLimbs extends AbstractGraveyardCard
{
  public static final String ID = makeID("ExtraLimbs");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
  
  private static final int COST = 1;
  
  public ExtraLimbs() {
	super(ID, COST, TYPE, RARITY, TARGET);
		
    this.baseDamage = 6;
    this.damage=this.baseDamage;
    this.baseMagicNumber = 2;
    this.magicNumber = this.baseMagicNumber;
    
    this.exhaust = true;
  }

  
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int x = 0; x < this.magicNumber; x++) {
			AbstractDungeon.actionManager
					.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
							AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	
	public void triggerOnExhaust() {
		this.baseMagicNumber++;
	}

  
  public void upp() {
    upgradeDamage(2);
  }
}
