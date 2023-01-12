package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReturnExhaustedToDeckAction
  extends AbstractGameAction
{
  private AbstractPlayer p;
  private final boolean random;
  public static final String ID = makeID("ReturnExhaustedToDeckAction");
  private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
  public static final String[] TEXT = uiStrings.TEXT;
  
  public ReturnExhaustedToDeckAction(boolean random) {
    this.classless = new ArrayList();

    this.random = random;
    this.p = AbstractDungeon.player;
    setValues(this.p, AbstractDungeon.player, this.amount);
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }
  private ArrayList<AbstractCard> classless;
  
  public void update() {
	  
    if(this.random) {
	    if (this.p.exhaustPile.isEmpty()) {
	        this.isDone = true;
	        return;
		}
    	
    	for (Iterator<AbstractCard> ex = this.p.exhaustPile.group.iterator(); ex.hasNext(); ) {
	        AbstractCard derp = (AbstractCard)ex.next();
	        if (derp.color.equals(AbstractCard.CardColor.COLORLESS) || derp.color.equals(AbstractCard.CardColor.CURSE)) {
	          this.classless.add(derp);
	          ex.remove();
	        } 
	    }
    	
	    if (this.p.exhaustPile.isEmpty()) {
	    	this.p.exhaustPile.group.addAll(classless);
	    	this.classless.clear();
	        this.isDone = true;
	        return;
		}
    	
        AbstractCard c = this.p.exhaustPile.getRandomCard(true);
        AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c, 1, true, true));
        this.p.exhaustPile.removeCard(c);
        
        this.p.exhaustPile.group.addAll(classless);
        this.classless.clear();
        this.isDone = true;
        
        return;
    }
    else {
    	if (this.duration == Settings.ACTION_DUR_FAST) {
	      if (this.p.exhaustPile.isEmpty()) {
	        this.isDone = true;
	        return;
	      }
	      
	    	for (Iterator<AbstractCard> ex = this.p.exhaustPile.group.iterator(); ex.hasNext(); ) {
		        AbstractCard derp = (AbstractCard)ex.next();
		        if (derp.color.equals(AbstractCard.CardColor.COLORLESS) || derp.color.equals(AbstractCard.CardColor.CURSE)) {
		          this.classless.add(derp);
		          ex.remove();
		        } 
		    }
	    	
	      if (this.p.exhaustPile.isEmpty()) {
	    	  this.p.exhaustPile.group.addAll(classless);
	    	  this.classless.clear();
		        this.isDone = true;
		        return;
	      }
	      
	      if (this.p.exhaustPile.size() == 1) {
	        AbstractCard c = this.p.exhaustPile.getTopCard();
	        c.unfadeOut();
	        AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c, 1, true, true));
	        this.p.exhaustPile.removeCard(c);
	        c.fadingOut = false;
	        
	    	this.p.exhaustPile.group.addAll(classless);
	    	this.classless.clear();
	        this.isDone = true;
	        
	        return;
	      } 
	      
	      for (AbstractCard c : this.p.exhaustPile.group) {
	        c.stopGlowing();
	        c.unfadeOut();
	      }
	      
	      AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
	      tickDuration();
	      
	      return;
	    } 
	    
	    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
	      for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
	        AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c, 1, true, true));
	        this.p.exhaustPile.removeCard(c);
	      } 
	      AbstractDungeon.gridSelectScreen.selectedCards.clear();
	      
	    	this.p.exhaustPile.group.addAll(classless);
	    	this.classless.clear();
	      
	      for (AbstractCard c : this.p.exhaustPile.group) {
	        c.unhover();
	        c.target_x = CardGroup.DISCARD_PILE_X;
	        c.target_y = 0.0F;
	      }
	    }
    } 
    

    
    tickDuration();
  }
}
