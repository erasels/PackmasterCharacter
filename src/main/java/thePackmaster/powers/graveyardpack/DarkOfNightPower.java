package thePackmaster.powers.graveyardpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DarkOfNightPower extends AbstractPackmasterPower {
	
    public static final String POWER_ID = makeID("DarkOfNightPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final PowerType POWER_TYPE = PowerType.BUFF;
    
    public DarkOfNightPower(AbstractCreature owner, int newAmount)
    {
    	super(POWER_ID,NAME,POWER_TYPE,true,AbstractDungeon.player,newAmount);
    	updateDescription();
    }
    
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.exhaust && this.amount > 0) {
          flash();
          AbstractMonster m = null;
          
          if (action.target != null) {
            m = (AbstractMonster)action.target;
          }
          
          AbstractCard tmp = card.makeSameInstanceOf();
          AbstractDungeon.player.limbo.addToBottom(tmp);
          tmp.current_x = card.current_x;
          tmp.current_y = card.current_y;
          tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
          tmp.target_y = Settings.HEIGHT / 2.0F;
          
          if (m != null) {
            tmp.calculateCardDamage(m);
          }
          
          tmp.purgeOnUse = true;
          AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);

          
          this.amount--;
          if (this.amount == 0) {
        	  AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
          }
        } 
      }
    

    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
    
	public void updateDescription() {
	    if (this.amount == 1) {
	        this.description = DESCRIPTIONS[0];
	      } else {
	        this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
	      } 
	}
	
}
