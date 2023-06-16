package thePackmaster.powers.lockonpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TunnelVisionPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(TunnelVisionPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    //Patches
    public TunnelVisionPower(AbstractCreature owner, int amt) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amt);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
