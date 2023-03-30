package thePackmaster.powers.frostpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.frostpack.ExtendedStallAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ExtendedStallPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ExtendedStallPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ExtendedStallPower(final AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        this.priority = 0;
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        Wiz.atb(new ExtendedStallAction(this.amount));
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}