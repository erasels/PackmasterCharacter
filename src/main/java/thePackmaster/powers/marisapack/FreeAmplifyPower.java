package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FreeAmplifyPower extends AbstractPackmasterPower implements CloneablePowerInterface, AmplifyPowerHook {
    public static final String POWER_ID = makeID(FreeAmplifyPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public FreeAmplifyPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onAmplify(AbstractCard c) {
        flashWithoutSound();
        Wiz.reducePower(this);
    }

    @Override
    public AbstractPower makeCopy() {
        return new FreeAmplifyPower(amount);
    }

    @Override
    public void updateDescription() {
        if(amount > 1) description = String.format(DESCRIPTIONS[1], amount);
        else description = DESCRIPTIONS[0];
    }

}




