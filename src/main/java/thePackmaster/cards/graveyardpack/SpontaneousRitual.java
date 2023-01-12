package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.BaseMod;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.graveyardpack.ExhaustFromHandAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpontaneousRitual extends AbstractGraveyardCard {
	public static final String ID = makeID("SpontaneousRitual");

	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private static final int COST = 1;

	public SpontaneousRitual() {
		super(ID, COST, TYPE, RARITY, TARGET);

		this.baseDamage = 0;
		this.damage = this.baseDamage;
		this.baseMagicNumber=0;
		this.magicNumber=this.baseMagicNumber;
		
	}

	  public void applyPowers() {
	    super.applyPowers();
	    
	    this.baseDamage=SpireAnniversary5Mod.combatExhausts;
	    this.damage=this.baseDamage;
	    this.baseMagicNumber=SpireAnniversary5Mod.combatExhausts;
		this.magicNumber=this.baseMagicNumber;
	    
	    if (this.magicNumber > 0) {
	    	if(this.upgraded) {
	    		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
	    	}else {
	    		this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
	    	}
	    	initializeDescription();
	    }
	    

	  }

	public void use(AbstractPlayer p, AbstractMonster m) {

	    this.baseDamage=SpireAnniversary5Mod.combatExhausts;
	    this.damage=this.baseDamage;
	    
	    int tempDamage=this.damage+p.hand.size();
	    if(p.hand.contains(this)) {
	    	tempDamage-= 1;
	    }

	    
		if(this.upgraded) {
				tempDamage+= 3 - java.lang.Math.max(p.hand.size()-BaseMod.MAX_HAND_SIZE+2, 0);	//when hand is too large, don't add the full 3 to damage (extra wounds are automatically discarded)
				AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Dazed(), 3));
		}
		
		AbstractDungeon.actionManager.addToBottom(new ExhaustFromHandAction(BaseMod.MAX_HAND_SIZE,true,false,false));	//p.hand.size() not updating correctly for some reason

	    CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
	    
	    
		DamageInfo d = new DamageInfo(p, tempDamage, this.damageTypeForTurn);
		d.applyPowers(p, m);
		
	    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, d, AbstractGameAction.AttackEffect.FIRE));

	    
	    if(AbstractDungeon.player.hasRelic("Unceasing Top"))	//hack to get around unceasing top not triggering correctly
		{
			  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
		}
	    
	}
	
	  public void calculateCardDamage(AbstractMonster mo) {
	    super.calculateCardDamage(mo);
	    if (SpireAnniversary5Mod.combatExhausts > 0) {
	    	if(this.upgraded) {
	    		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
	    	}else {
	    		this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
	    	}
	    }
    	initializeDescription();
	  }
	
	public void onMoveToDiscard() {
		if(this.upgraded) {
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		}else {
			this.rawDescription = cardStrings.DESCRIPTION;
		}
		
	    initializeDescription();
	}
	
	public void triggerOnExhaust(){
		if(this.upgraded) {
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		}else {
			this.rawDescription = cardStrings.DESCRIPTION;
		}
		
	    initializeDescription();
	}

	public void upp() {
	}
}
