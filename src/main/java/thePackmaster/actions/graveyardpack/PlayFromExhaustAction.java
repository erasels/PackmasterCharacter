package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;



public class PlayFromExhaustAction extends AbstractGameAction
{
	private final AbstractPlayer p;
	private final ArrayList<AbstractCard> classless;
	private final boolean random;
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PlayFromExhaustAction"));
	public static final String[] TEXT = uiStrings.TEXT;
	
	public PlayFromExhaustAction(boolean random) {
		this.random = random;
		this.classless = new ArrayList<>();
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, this.amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
	
	public PlayFromExhaustAction(){
		this(false);
	}

	@Override
	public void update() {
    	if (this.duration == Settings.ACTION_DUR_FAST) {
  	      if (this.p.exhaustPile.isEmpty()) {
  	        this.isDone = true;
  	        return;
  	      }
  	      //set aside colorless cards, curses, and cards you can't play
  	    	for (Iterator<AbstractCard> ex = this.p.exhaustPile.group.iterator(); ex.hasNext(); ) {
  		        AbstractCard derp = ex.next();
  		        if (derp.color.equals(AbstractCard.CardColor.COLORLESS) || derp.color.equals(AbstractCard.CardColor.CURSE) || !derp.canUse(p, null)) {
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
  	        playCard(c);
  	        
  	    	this.p.exhaustPile.group.addAll(classless);
  	    	this.classless.clear();
  	    	this.isDone=true;
  	        
  	        return;
  	      } 
  	      
  	      if(this.random) {
  	    	  playCard(p.exhaustPile.getRandomCard(true));
  	    	  this.p.exhaustPile.group.addAll(classless);
  	    	  this.classless.clear();
  	    	  this.isDone = true;
  	    	  return;
  	      }
  	      
  	      
  	      for (AbstractCard cx : this.p.exhaustPile.group) {
  	        cx.stopGlowing();
  	        cx.unfadeOut();
  	      }
  	      AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
  	      
  	      tickDuration();
  	      return;
  	    } 
  	    
  	    if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
  	      for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
  	    	playCard(c);
  	      } 
  	      AbstractDungeon.gridSelectScreen.selectedCards.clear();
  	      
  	    	this.p.exhaustPile.group.addAll(classless);
  	    	this.classless.clear();
  	      
  	      for (AbstractCard cx : this.p.exhaustPile.group) {
  	        cx.unhover();
  	        cx.target_x = CardGroup.DISCARD_PILE_X;
  	        cx.target_y = 0.0F;
  	      }
  	    }
  	    

		tickDuration();
	}
	
	public static void playCard(AbstractCard c) {
		AbstractMonster t = AbstractDungeon.getMonsters().getRandomMonster(true);
		
		c.applyPowers();
		
		//make a temporary copy and play that
        AbstractCard tmp = c.makeSameInstanceOf();
        tmp.magicNumber = c.magicNumber;
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = Settings.WIDTH / 2.0F;
        tmp.current_y = Settings.HEIGHT / 2.0F;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F;
        
        if (t != null) {
          tmp.calculateCardDamage(t);
        }
        
        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, t, AbstractDungeon.player.energy.energy, true, true), true);

        
		//clean up the original card
		AbstractDungeon.player.exhaustPile.removeCard(c);
		for (AbstractCard d : AbstractDungeon.player.exhaustPile.group) {
			d.stopGlowing();
			d.unfadeOut();
		}
        
		if (!Settings.FAST_MODE) {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
		} else {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
		}
	}
}