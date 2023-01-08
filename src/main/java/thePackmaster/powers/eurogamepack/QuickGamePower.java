package thePackmaster.powers.eurogamepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import thePackmaster.powers.AbstractPackmasterPower;

public class QuickGamePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("QuickGamePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int MIN_VALUE = 100;

    public QuickGamePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        MIN_VALUE = Math.round(100/(2^this.amount));
            if (MIN_VALUE == 0){
                MIN_VALUE = 1;}

    }
    //This is for the sake of display, functionality is in VictoryPoints

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + MIN_VALUE + DESCRIPTIONS[1];
    }
}


