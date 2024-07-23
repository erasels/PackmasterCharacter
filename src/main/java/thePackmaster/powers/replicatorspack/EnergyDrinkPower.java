package thePackmaster.powers.replicatorspack;


import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class EnergyDrinkPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EnergyDrinkPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public EnergyDrinkPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME, AbstractPower.PowerType.BUFF,false,owner,amount);

    }

    @Override
    public AbstractPower makeCopy() {
        return new IterativeDesignPower(this.owner, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}




