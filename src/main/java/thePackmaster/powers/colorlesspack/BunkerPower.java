package thePackmaster.powers.colorlesspack;


import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.cards.colorlesspack.GolfBall;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.makeInHand;

public class BunkerPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("BunkerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BunkerPower() {
        super(POWER_ID, NAME, PowerType.BUFF, true, AbstractDungeon.player, 1);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        makeInHand(new GolfBall(), amount);
        removeThis();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
