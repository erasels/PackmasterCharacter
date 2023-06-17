package thePackmaster.powers.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.summonerspellspack.SnowballDash;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SnowballStrikePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("SnowballStrikePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SnowballStrikePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for(int i = 0; i < this.amount; ++i)
                this.addToBot(new MakeTempCardInHandAction(new SnowballDash(), 1, false));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, SnowballStrikePower.POWER_ID));
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = this.amount == 1 ?
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] :
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

}
