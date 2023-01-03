package thePackmaster.powers.bardinspirepack;


import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;


public class SplendidPower extends AbstractPackmasterPower implements NonStackablePower, CloneablePowerInterface
{
    public static final String POWER_ID = makeID("SplendidForm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public SplendidPower(AbstractCreature owner, int inspiration, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        isTwoAmount = true;
        amount2 = inspiration;
        updateDescription();
    }

    @Override
    public void updateDescription()
    {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount2 + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurn()
    {
        atb(new ApplyPowerAction(p(), p(), new InspirationPower(p(), amount, amount2), amount));
    }

    @Override
    public boolean isStackable(AbstractPower power)
    {
        if (power instanceof SplendidPower) {
            return amount2 == ((SplendidPower) power).amount2;
        }
        return false;
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new SplendidPower(owner, amount2, amount);
    }
}
