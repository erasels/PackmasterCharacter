package thePackmaster.powers.entropypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DiffusionPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DiffusionPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DiffusionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        this.isTwoAmount = true;
        this.amount2 = 1;
        AbstractDungeon.player.gameHandSize += 1;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        ++this.amount2;
    }

    public void onRemove() {
        AbstractDungeon.player.gameHandSize -= this.amount2;
    }

    @Override
    public void atStartOfTurn() {
        this.flashWithoutSound();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new EntropyPower(this.owner, this.amount), this.amount2));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + (this.amount2 == 1 ? DESCRIPTIONS[2] : DESCRIPTIONS[3]);
    }
}