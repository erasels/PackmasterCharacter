package thePackmaster.powers.lockonpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LightningRodPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(LightningRodPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public LightningRodPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        for (AbstractMonster m : Wiz.getEnemies())
        {
            if (m != owner && m.hasPower(POWER_ID)) addToTop(new RemoveSpecificPowerAction(m, owner, POWER_ID));
        }
    }

    public void updateDescription() {
        this.description = amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], amount);
    }
    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }
}
