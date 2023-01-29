package thePackmaster.powers.bitingcoldpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class GlaciatePower extends AbstractPackmasterPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = makeID("GlaciatePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GlaciatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower pow, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if(FrostbitePower.POWER_ID.equals(pow.ID)) {
            flash();
            if (Settings.FAST_MODE)
                addToBot(new WaitAction(0.1F));
            else
                addToBot(new WaitAction(0.2F));
            applyToSelf(new VigorPower(owner, amount));
        }
        return true;
    }


}