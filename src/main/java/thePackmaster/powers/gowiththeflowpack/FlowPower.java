package thePackmaster.powers.gowiththeflowpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.gowiththeflowpack.FlowDrawAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.transmutationpack.TransmutableAffectingPower;

public class FlowPower extends AbstractPackmasterPower implements TransmutableAffectingPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("FlowPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FlowPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount > 1) {
            description = DrawCardNextTurnPower.DESCRIPTIONS[0] + amount + DrawCardNextTurnPower.DESCRIPTIONS[1];
        } else {
            description = DrawCardNextTurnPower.DESCRIPTIONS[0] + amount + DrawCardNextTurnPower.DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new FlowDrawAction(this));
    }
}
