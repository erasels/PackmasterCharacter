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

public class ExhaustHandAction extends AbstractGameAction {
  
  private AbstractPlayer p;
  
  public ExhaustHandAction() {
    this.p = AbstractDungeon.player;
    this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    this.actionType = AbstractGameAction.ActionType.EXHAUST;
  }
  
  public void update() {
    if (this.duration == this.startDuration) {
    	int cards = p.hand.size();
	    for (int i = 0; i < cards; i++) {
	      AbstractCard c = this.p.hand.getTopCard();
	      p.hand.moveToExhaustPile(c);
	    } 
		AbstractDungeon.player.hand.refreshHandLayout();
	    CardCrawlGame.dungeon.checkForPactAchievement();
    }
    
    tickDuration();
  }
}