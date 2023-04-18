package thePackmaster.powers.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AntihealPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AntihealPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AntihealPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public int onHeal(int healAmount) {
        this.flash();

        if (healAmount >= this.amount) {
            healAmount -= this.amount;
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, AntihealPower.POWER_ID));
            return healAmount;
        }

        this.addToBot(new ReducePowerAction(this.owner, this.owner, AntihealPower.POWER_ID, healAmount));
        return 0;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
