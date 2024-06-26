package thePackmaster.powers.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SkipNextTurnPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(SkipNextTurnPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SkipNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn()
    {
        if(owner instanceof AbstractPlayer)
        {
            if (this.amount <= 1)
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            else
                addToBot(new ReducePowerAction(owner, owner, this, 1));

            addToBot(new PressEndTurnButtonAction());
        }
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1]+ amount + DESCRIPTIONS[2];
    }

}
