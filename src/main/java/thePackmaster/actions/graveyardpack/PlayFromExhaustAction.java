package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
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
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import thePackmaster.actions.graveyardpack.CleanUpCardAction;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;



public class PlayFromExhaustAction extends AbstractGameAction
{
	private AbstractPlayer p;
	private ArrayList<AbstractCard> classless;
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PlayFromExhaustAction"));
	public static final String[] TEXT = uiStrings.TEXT;
	
	public PlayFromExhaustAction() {
		this.classless = new ArrayList();
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, this.amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
	  
	public void update() {
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
  	        playCard(c);
  	        
  	    	this.p.exhaustPile.group.addAll(classless);
  	    	this.classless.clear();
  	    	this.isDone=true;
  	        
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
  	    
  	    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
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
		
		
//		c.freeToPlayOnce = true;
//		c.purgeOnUse=true;
		
		c.applyPowers();
//		c.freeToPlayOnce=true;
//		c.purgeOnUse=true;
		
		/*
		AbstractCard c2 = c.makeStatEquivalentCopy();
        c2.current_y = -200.0F * Settings.scale;
        c2.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
        c2.target_y = Settings.HEIGHT / 2.0F;
        c2.targetAngle = 0.0F;
        c2.lighten(false);
        c2.drawScale = 0.12F;
        c2.targetDrawScale = 0.75F;
        AbstractDungeon.actionManager.addToBottom(new ShowCardAndPoofAction(c2));
		*/
		
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

        
		//AbstractDungeon.actionManager.addToTop(new NewQueueCardAction(c, t, false, true));
		AbstractDungeon.actionManager.addToTop(new CleanUpCardAction(c, "exhaust", "nowhere", -1));
		if (!Settings.FAST_MODE) {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
		} else {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
		}
	}
}