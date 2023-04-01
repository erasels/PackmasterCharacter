package thePackmaster.powers.fueledpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ControlledBurnPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(ControlledBurnPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    int counter;

    public ControlledBurnPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
        counter = amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        counter += stackAmount;
    }

    public void onConsume() {
        if (counter == 0)
            return;
        att(new DrawCardAction(1));
        att(new GainEnergyAction(1));
        counter--;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}