package thePackmaster.powers.scrypluspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AnticipatePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(AnticipatePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int vigorAmt = 3;
    public AnticipatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ScryCallbackAction(amount, list ->
            list.forEach(c ->
                    addToBot(new ApplyPowerAction(owner, owner, new VigorPower(owner, vigorAmt)))
            )));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, vigorAmt);
    }
}
