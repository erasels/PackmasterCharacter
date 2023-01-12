package thePackmaster.actions.graveyardpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;



public class CleanUpCardAction extends AbstractGameAction
{
	private final AbstractCard card;
	private final int depth;
	private final String from;
	private final String to;
	private AbstractPlayer p;
	
	public CleanUpCardAction(AbstractCard card, String from, String to, int depth) {
		this.card=card;
		this.p= AbstractDungeon.player;
		this.depth = depth;
		this.from = from;
		this.to=to;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FASTER;
	}
  
	public void update() {
		if(depth>0) {
			AbstractDungeon.actionManager.addToBottom(new CleanUpCardAction(this.card, this.from, this.to, this.depth-1));
		}else {
			if(from == "exhaust");{
				if(to == "discard") {
					AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.card, 1));
					this.p.exhaustPile.removeCard(this.card);
					for (AbstractCard c : this.p.discardPile.group) {
						c.stopGlowing();
						c.unfadeOut();
					}
					
				}else {
					this.p.exhaustPile.removeCard(this.card);
					for (AbstractCard c : this.p.exhaustPile.group) {
						c.stopGlowing();
						c.unfadeOut();
					}
				}
			}
		}
		
		this.isDone = true;
	}
}