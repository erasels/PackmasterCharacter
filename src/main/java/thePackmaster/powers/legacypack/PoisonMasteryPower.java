package thePackmaster.powers.legacypack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PoisonMasteryPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PoisonMasteryPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public PoisonMasteryPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * 100) + DESCRIPTIONS[1];
    }

}
