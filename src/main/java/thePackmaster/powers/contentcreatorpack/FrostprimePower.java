package thePackmaster.powers.contentcreatorpack;


import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FrostprimePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("FrostprimePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostprimePower(int amount, int amount2) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    public void stackAmount2(int amount2) {
        if (this.amount2 != -1 && amount2 != -1) {
            this.amount2 += amount2;
        }
    }

    public void atStartOfTurnPostDraw() {
        flash();
        for (int i = 0; i < amount; i++) {
            atb(new ChannelAction(new Frost()));
        }
        atb(new AllEnemyApplyPowerAction(owner, amount2, (mon) -> new FrostbitePower(mon, amount2)));
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        sb.append(amount);
        sb.append(DESCRIPTIONS[1]);
        sb.append(amount2);
        sb.append(DESCRIPTIONS[2]);
        description = sb.toString();
    }
}
