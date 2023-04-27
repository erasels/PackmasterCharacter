package thePackmaster.powers.serpentinepack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class TaintedEnemy extends AbstractPackmasterPower implements OnReceivePowerPower {

    public static final String POWER_ID = SpireAnniversary5Mod.makeID("TaintedEnemy");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TaintedEnemy(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + (this.amount + 1) + DESCRIPTIONS[2];
        }
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower.type == PowerType.DEBUFF && abstractCreature1 == AbstractDungeon.player && !abstractCreature.hasPower(ArtifactPower.POWER_ID)) {
            flashWithoutSound();
            abstractPower.amount *= (this.amount + 1);
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power.type == PowerType.DEBUFF && source == AbstractDungeon.player && !target.hasPower(ArtifactPower.POWER_ID)) {
            this.flashWithoutSound();
            return stackAmount * (this.amount + 1);
        }
        return stackAmount;
    }
}
