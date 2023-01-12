package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ExhaustFromHandAction extends AbstractGameAction {
  
  private AbstractPlayer p;
  
  private boolean isRandom;
  private String shadeCard="";
  
  public ExhaustFromHandAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
    this.anyNumber = anyNumber;
    this.p = AbstractDungeon.player;
    this.canPickZero = canPickZero;
    this.isRandom = isRandom;
    this.amount = amount;
    this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    this.actionType = AbstractGameAction.ActionType.EXHAUST;
  }
  
  public ExhaustFromHandAction(String shadeCard) {
		  if(shadeCard=="Excise") {
			    this.anyNumber = false;
			    this.p = AbstractDungeon.player;
			    this.canPickZero = false;
			    this.isRandom = false;
			    this.amount = 1;
			    this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
			    this.actionType = AbstractGameAction.ActionType.EXHAUST;
			    this.shadeCard=shadeCard;
		  }
	 }

  
  private boolean anyNumber;
  private boolean canPickZero;
  public static int numExhausted;
  
  public ExhaustFromHandAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
    this(amount, isRandom, anyNumber);
    this.target = target;
    this.source = source;
  }
  
  public ExhaustFromHandAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
    this(amount, isRandom, false, false);
    this.target = target;
    this.source = source;
  }






  
  public ExhaustFromHandAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
    this(amount, isRandom, anyNumber, canPickZero);
    this.target = target;
    this.source = source;
  }

  
  public ExhaustFromHandAction(boolean isRandom, boolean anyNumber, boolean canPickZero) { this(99, isRandom, anyNumber, canPickZero); }


  
  public ExhaustFromHandAction(int amount, boolean canPickZero) { this(amount, false, false, canPickZero); }


  
  public ExhaustFromHandAction(int amount, boolean isRandom, boolean anyNumber) { this(amount, isRandom, anyNumber, false); }

  
  public ExhaustFromHandAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
    this(amount, isRandom, anyNumber, canPickZero);
    this.duration = this.startDuration = duration;
  }

  
  public void update() {
    if (this.duration == this.startDuration) {

      
      if (this.p.hand.size() == 0) {
        this.isDone = true;
        
        return;
      } 
      if (!this.anyNumber && 
        this.p.hand.size() <= this.amount) {
        this.amount = this.p.hand.size();
        numExhausted = this.amount;
        int tmp = this.p.hand.size();
        for (int i = 0; i < tmp; i++) {
          AbstractCard c = this.p.hand.getTopCard();
          this.p.hand.moveToExhaustPile(c);
          if(this.shadeCard=="Excise") {
        	  if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
        		    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        		    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.p,1));
        	  }
          }
        } 
        CardCrawlGame.dungeon.checkForPactAchievement();
        
        return;
      } 
      
      if (this.isRandom) {
        for (int i = 0; i < this.amount; i++) {
          this.p.hand.moveToExhaustPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
        }
        CardCrawlGame.dungeon.checkForPactAchievement();
      } else {
        numExhausted = this.amount;
        AbstractDungeon.handCardSelectScreen.open("Error, pick cards to exhaust.", this.amount, this.anyNumber, this.canPickZero);
        tickDuration();
        
        return;
      } 
      
    } 
    
    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
        this.p.hand.moveToExhaustPile(c);
        if(this.shadeCard=="Excise") {
      	  if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
      		    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
      		    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.p,1));
      	  }
        }
      }
      CardCrawlGame.dungeon.checkForPactAchievement();
      AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
    } 
    
    tickDuration();
  }
}