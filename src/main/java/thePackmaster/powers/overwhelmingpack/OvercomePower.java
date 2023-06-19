package thePackmaster.powers.overwhelmingpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OvercomePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("OvercomePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public OvercomePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard original = card.makeCopy();
        for (int i = 0; i < card.timesUpgraded; ++i)
            SuperUpgradeAction.forceUpgrade(original, false);

        if (original.cost > EnergyPanel.getCurrentEnergy()) {
            this.flash();
            addToTop(new GainBlockAction(this.owner, this.owner, this.amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}