package thePackmaster.powers.frostpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrimePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PrimePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public PrimePower(final AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        this.priority = 0;
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        for (int i = 0; i < amount; i++) {
            if (orb instanceof PackmasterOrb) ((PackmasterOrb) orb).passiveEffect();
            orb.onStartOfTurn();
            orb.onEndOfTurn();
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}