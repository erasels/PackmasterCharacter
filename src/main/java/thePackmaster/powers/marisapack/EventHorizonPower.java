package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EventHorizonPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(EventHorizonPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public EventHorizonPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.applyToSelf(new VigorPower(owner, amount));
        flashWithoutSound();
    }

    @Override
    public AbstractPower makeCopy() {
        return new EventHorizonPower(amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}




