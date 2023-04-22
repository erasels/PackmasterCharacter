package thePackmaster.powers.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static org.apache.commons.lang3.math.NumberUtils.min;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RemedyPower extends AbstractPackmasterPower implements OnReceivePowerPower {
    public static final String POWER_ID = makeID("RemedyPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RemedyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && !owner.hasPower(ArtifactPower.POWER_ID) && this.amount > 0){
            flashWithoutSound();
            int incr = min(2, abs(power.amount));
            if (power.amount > 0) {
                Wiz.att(new ReducePowerAction(target, source, power.ID, incr));
                this.reducePower(1);
            } else if (owner.getPower(power.ID).amount + power.amount == 0){
                power.type = PowerType.BUFF;
                Wiz.atb(new ApplyPowerAction(owner, owner, power, power.amount));
                this.reducePower(1);
                power.amount = incr;
            }
            else if (power.amount < 0) {
                power.type = PowerType.BUFF;
                Wiz.atb(new ApplyPowerAction(owner, owner, power, incr, false));
                this.reducePower(1);
            }
        }
        if (amount <= 0)
            removeThis();
        this.updateDescription();
        return true;
    }

    @Override
    public void updateDescription() {
        if (amount != 1) {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];
        }
    }


}
