package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import thePackmaster.actions.graveyardpack.CleanUpCardAction;

import java.util.ArrayList;
import java.util.Iterator;



public class PlayRandomFromExhaustAction extends AbstractGameAction
{
	private AbstractPlayer p;
	private final boolean toDiscard;
	
	public PlayRandomFromExhaustAction(boolean toDiscard) {
		this.misfits = new ArrayList();
		this.toDiscard = toDiscard;
		this.p = AbstractDungeon.player;
		setValues(this.p, AbstractDungeon.player, this.amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
	}
	private ArrayList<AbstractCard> misfits;
  
	public void update() {
		if (this.p.exhaustPile.isEmpty()) {
		    this.isDone = true;
		    return;
		}
		
		for (Iterator<AbstractCard> ex = this.p.exhaustPile.group.iterator(); ex.hasNext(); ) {
			AbstractCard derp = (AbstractCard)ex.next();
			// Exclude a card if it's the wrong type, if it's a status, if it's a curse, or if you can't play it.
			if (derp.color.equals(AbstractCard.CardColor.COLORLESS) || derp.color.equals(AbstractCard.CardColor.CURSE) || !derp.canUse(p, AbstractDungeon.getMonsters().getRandomMonster(true))) {
				this.misfits.add(derp);
				ex.remove();
			} 
		}
		
		if (this.p.exhaustPile.isEmpty()) {
			this.p.exhaustPile.group.addAll(misfits);
			this.misfits.clear();
		    this.isDone = true;
		    return;
		}
		
		AbstractCard c = this.p.exhaustPile.getRandomCard(true);

		playCard(c, this.toDiscard);
		
		this.p.exhaustPile.group.addAll(misfits);
		this.misfits.clear();
	    
	    this.isDone=true;
	}
	
	public static void playCard(AbstractCard c, boolean toDiscard) {
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
        if(toDiscard) {
        	AbstractDungeon.actionManager.addToTop(new CleanUpCardAction(c, "exhaust", "discard", -1));
        }else {
        	AbstractDungeon.actionManager.addToTop(new CleanUpCardAction(c, "exhaust", "nowhere", -1));
        }
		if (!Settings.FAST_MODE) {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
		} else {
			AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
		}
	}
}