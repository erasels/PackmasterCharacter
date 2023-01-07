package thePackmaster.powers.bitingcoldpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GlaciatePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GlaciatePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GlaciatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    // Hey there! Welcome to my code!
    // I know that this power doesn't look to be doing anything...
    // Its functionality is actually in SpireAnniversary5Mod, in receivePostPowerApplySubscriber()!
    // If you want to see this power's code, go there ^^

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}