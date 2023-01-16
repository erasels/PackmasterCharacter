package thePackmaster.powers.summonspack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.adp;

public class TrainingPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(TrainingPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public TrainingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!adp().orbs.isEmpty() && !(adp().orbs.get(0) instanceof EmptyOrbSlot)) {
            flash();

            for(int i = 0; i < amount; ++i)
                adp().orbs.get(0).onEvoke();
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}