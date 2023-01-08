package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InertiaPower extends AbstractPackmasterPower implements CloneablePowerInterface, AmplifyPowerHook {
    public static final String POWER_ID = makeID(InertiaPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public InertiaPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        Wiz.applyToSelf(new ChargeUpPower(amount));
        flashWithoutSound();
    }

    @Override
    public void onAmplify(AbstractCard c) {
        Wiz.applyToSelf(new ChargeUpPower(amount));
        flashWithoutSound();
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChargeUpPower(amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}




