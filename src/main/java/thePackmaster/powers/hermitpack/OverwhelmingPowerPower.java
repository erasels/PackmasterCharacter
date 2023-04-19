package thePackmaster.powers.hermitpack;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OverwhelmingPowerPower extends AbstractPackmasterPower {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("OverwhelmingPowerPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public OverwhelmingPowerPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.DEBUFF, false, owner, amount);
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        if (EnergyPanel.totalCount == 0) {
            this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, amount));
        }
    }

    public void updateDescription() {
            description = DESCRIPTIONS[0] + (amount) + DESCRIPTIONS[1];
    }
}
