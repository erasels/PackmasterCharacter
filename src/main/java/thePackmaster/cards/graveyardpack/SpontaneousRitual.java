package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.UnceasingTop;

import basemod.BaseMod;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.graveyardpack.ExhaustHandAction;

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
	
	private int sumDamage() {
		int tempDamage=SpireAnniversary5Mod.combatExhausts+AbstractDungeon.player.hand.size();
	    if(AbstractDungeon.player.hand.contains(this)) {
	    	tempDamage-= 1;
	    }
	    
		if(this.upgraded) {
			tempDamage+= 3 - java.lang.Math.max(AbstractDungeon.player.hand.size()-BaseMod.MAX_HAND_SIZE+2, 0);	//when hand is too large, don't add the full 3 to damage (extra wounds are automatically discarded)
		}
	    
		return tempDamage;
	}

	  public void applyPowers() {
		this.baseDamage=sumDamage();
		
	    super.applyPowers();

	    
    	if(this.upgraded) {
    		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
    	}else {
    		this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
    	}
    	initializeDescription();
	    
	  }

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ExhaustHandAction());

		AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_DARK_EVOKE"));
		
		if(this.upgraded) {
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Dazed(), 3));
		}
		
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
	}
	
	  public void calculateCardDamage(AbstractMonster mo) {

	    this.baseDamage = sumDamage();
		  
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
		cardsToPreview = new Dazed();
	}
}
