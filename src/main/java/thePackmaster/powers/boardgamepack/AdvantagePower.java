package thePackmaster.powers.boardgamepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AdvantagePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(AdvantagePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AdvantagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1]+ amount + DESCRIPTIONS[2];
    }

}
