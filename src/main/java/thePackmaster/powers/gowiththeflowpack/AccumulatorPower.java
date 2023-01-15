package thePackmaster.powers.gowiththeflowpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class AccumulatorPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("AccumulatorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AccumulatorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onInitialApplication() {
        BaseMod.MAX_HAND_SIZE += amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE += stackAmount;
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        BaseMod.MAX_HAND_SIZE -= reduceAmount;
    }

    @Override
    public void onVictory() {
        BaseMod.MAX_HAND_SIZE -= amount;
    }

    @Override
    public void onRemove() {
        BaseMod.MAX_HAND_SIZE -= amount;
    }
}
