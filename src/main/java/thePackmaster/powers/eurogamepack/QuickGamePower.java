package thePackmaster.powers.eurogamepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import thePackmaster.powers.AbstractPackmasterPower;

public class QuickGamePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("QuickGamePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public QuickGamePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    //Functionality is in VictoryPoints

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}


