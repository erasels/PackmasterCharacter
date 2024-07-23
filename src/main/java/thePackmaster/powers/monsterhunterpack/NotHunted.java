package thePackmaster.powers.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NotHunted extends AbstractPackmasterPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "NotHunted";
    public static final String POWER_ID = makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NotHunted(AbstractCreature owner, int amount) {
        super(POWER_ID, LOC_NAME, PowerType.BUFF, true, owner, amount);
        name = LOC_NAME;
        canGoNegative = false;
        updateDescription();
    }

    public void atEndOfRound() {
        if (this.amount <= 1) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }


    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return this.owner != null && !this.owner.isPlayer ? damage * (1 - (amount * 0.1f)) : damage;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount * 10 + DESCRIPTIONS[1];
    }
}