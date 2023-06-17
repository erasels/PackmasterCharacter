package thePackmaster.powers.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RemedyPower extends AbstractPackmasterPower implements OnReceivePowerPower {
    public static final String POWER_ID = makeID("RemedyPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public static final int REDUCE_AMOUNT = 2;

    public RemedyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        boolean apply = true;
        if (power.type == PowerType.DEBUFF && !owner.hasPower(ArtifactPower.POWER_ID) && this.amount > 0){
            flashWithoutSound();
            int reduction = Math.min(REDUCE_AMOUNT, Math.abs(power.amount));
            if (power.amount > 0) {
                power.amount -= reduction;
            } else {
                power.amount += reduction;
            }

            if (power.amount == 0) {
                apply = false;
            }

            this.reducePower(1);
        }
        if (this.amount <= 0) {
            removeThis();
        }
        this.updateDescription();
        return apply;
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
