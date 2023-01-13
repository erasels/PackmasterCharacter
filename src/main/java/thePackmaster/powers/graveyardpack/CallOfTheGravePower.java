package thePackmaster.powers.graveyardpack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import thePackmaster.actions.graveyardpack.PlayFromExhaustAction;
import thePackmaster.powers.AbstractPackmasterPower;


public class CallOfTheGravePower extends AbstractPackmasterPower {
	
    public static final String POWER_ID = makeID("CallOfTheGravePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final PowerType POWER_TYPE = PowerType.BUFF;
    
    public CallOfTheGravePower(AbstractCreature owner)
    {
    	super(POWER_ID,NAME,POWER_TYPE,false,AbstractDungeon.player,1);
    	updateDescription();
    }
    

    public void atStartOfTurnPostDraw() {
    	flash();
    	for(int i=0;i<this.amount;i++) {
    		AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_FAST));
    		AbstractDungeon.actionManager.addToBottom(new PlayFromExhaustAction(true));
    	}
    }
    
	public void updateDescription() {
		if(this.amount==1) {
			this.description = DESCRIPTIONS[0];
		} else {
			this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
		}
	}
	
}
