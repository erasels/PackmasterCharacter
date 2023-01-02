package thePackmaster.powers.shamanpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.shamanpack.FreezeAndBurn;
import thePackmaster.powers.AbstractPackmasterPower;

import java.text.MessageFormat;

public class FreezeAndBurnPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("FreezeAndBurn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private FreezeAndBurn freezeAndBurn;

    public FreezeAndBurnPower(AbstractCreature owner, FreezeAndBurn freezeAndBurn) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, 0);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.freezeAndBurn = (FreezeAndBurn)freezeAndBurn.makeSameInstanceOf();
        this.updateDescription();
        this.priority = 50;
    }

    @Override
    public void updateDescription() {
        this.description = this.freezeAndBurn == null ? "" : MessageFormat.format(DESCRIPTIONS[0], this.freezeAndBurn.baseDamage);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.freezeAndBurn.purgeOnUse = true;
        this.freezeAndBurn.nextTurnEffect = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this.freezeAndBurn, null, this.freezeAndBurn.energyOnUse, true, true), true);
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}