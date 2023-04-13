package thePackmaster.powers.quantapack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.*;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class IchorGobletPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("IchorGobletPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public IchorGobletPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, amount);
    }

    public void atStartOfTurn() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        this.addToBot(new GainEnergyAction(1));
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.flash();

            this.addToTop(new RemoveSpecificPowerAction(this.owner,this.owner,this));
            this.addToTop(new GainEnergyAction(this.amount));
        }

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
