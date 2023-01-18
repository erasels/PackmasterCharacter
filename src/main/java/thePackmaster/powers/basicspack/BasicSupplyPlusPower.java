package thePackmaster.powers.basicspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.actions.basicspack.KitSelectAction;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Strike;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicSupplyPlusPower extends AbstractPackmasterPower {
        public static final String POWER_ID = makeID("BasicSupplyPlusPower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        private final AbstractCard strike = new Strike();
    private final AbstractCard defend = new Defend();
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BasicSupplyPlusPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        this.strike.upgrade();
        this.defend.upgrade();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (int i = 0; i < this.amount; i++)
                addToBot(new KitSelectAction(true, strike, defend));
        }
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1)
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        else description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }
}
