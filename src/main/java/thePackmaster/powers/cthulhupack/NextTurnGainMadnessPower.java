package thePackmaster.powers.cthulhupack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NextTurnGainMadnessPower extends AbstractPackmasterPower implements InvisiblePower, NonStackablePower {
    public static final String POWER_ID = makeID("NextTurnGainMadnessPower");
    public static final String NAME = "";


    public NextTurnGainMadnessPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

    }

    public void atStartOfTurn() {
        Wiz.atb(new AddTemporaryHPAction(Wiz.p(), Wiz.p(), amount));
        Wiz.atb(new GainEnergyAction(3));
        Wiz.atb(new ChangeStanceAction(new NightmareStance()));
        removeThisInvisibly();
    }
}
