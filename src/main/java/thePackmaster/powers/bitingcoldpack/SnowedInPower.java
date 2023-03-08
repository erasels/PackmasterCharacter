package thePackmaster.powers.bitingcoldpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SnowedInPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("SnowedInPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SnowedInPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    // Hey there! Welcome to my code!
    // I know that this power doesn't look to be doing anything...
    // Its functionality is actually in the FrostbiteDamageAction
    // If you want to see this power's code, go there ^^

    @Override
    public void atEndOfRound() {
        atb(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
    }
}