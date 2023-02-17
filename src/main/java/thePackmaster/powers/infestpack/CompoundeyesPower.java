package thePackmaster.powers.infestpack;


import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CompoundeyesPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("CompoundeyesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CompoundeyesPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, -1);
        canGoNegative = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        addToBot(new PressEndTurnButtonAction());
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
