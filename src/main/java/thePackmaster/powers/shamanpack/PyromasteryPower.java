package thePackmaster.powers.shamanpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import java.text.MessageFormat;

public class PyromasteryPower extends AbstractPackmasterPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("Pyromastery");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PyromasteryPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = MessageFormat.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        return true;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power instanceof IgnitePower) {
            AbstractPower pyromasteryPower = AbstractDungeon.player.getPower(PyromasteryPower.POWER_ID);
            int extraIgnite = pyromasteryPower != null ? pyromasteryPower.amount : 0;
            power.amount = power.amount + extraIgnite;
            return stackAmount + extraIgnite;
        }
        return stackAmount;
    }
}