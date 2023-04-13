package thePackmaster.powers.cthulhupack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EndOfDaysPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("EndOfDaysPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public EndOfDaysPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (EnergyPanel.getCurrentEnergy() > 0) flash();
            for (int i = 0; i < EnergyPanel.getCurrentEnergy() * amount; i++) {
                addToBot(new ChannelAction(new Dark()));
            }

        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
