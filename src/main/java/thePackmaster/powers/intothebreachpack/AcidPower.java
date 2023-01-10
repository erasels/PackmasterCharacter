package thePackmaster.powers.intothebreachpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AcidPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AcidPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AcidPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        this.priority = 25;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage*2 : damage;
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0)
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        else
            addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}