package thePackmaster.powers.coresetpack;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MayhemFormPower extends AbstractPackmasterPower implements OnRefreshHandPower {
    public static final String POWER_ID = makeID("MayhemFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MayhemFormPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        updateDescription();
    }

    public boolean activatedThisTurn = false;

    @Override
    public void atStartOfTurnPostDraw() {
        activatedThisTurn = false;
        updateDescription();
    }

    @Override
    public void onRefreshHand() {
        if (canTrigger()) {
            activatedThisTurn = true;
            this.flash();
            addToBot(new DrawCardAction(amount));
            addToBot(new GainEnergyAction(amount));
            updateDescription();
        }
    }

    private boolean canTrigger() {
        return AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.player.hand.size() < 3 && !AbstractDungeon.actionManager.turnHasEnded && !activatedThisTurn && !AbstractDungeon.isScreenUp && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        sb.append(amount);
        sb.append(DESCRIPTIONS[1]);
        for (int i = 0; i < amount; i++) {
            sb.append(DESCRIPTIONS[2]);
        }
        sb.append(DESCRIPTIONS[3]);
        sb.append(activatedThisTurn ? DESCRIPTIONS[5] : DESCRIPTIONS[4]);
        description = sb.toString();
    }
}
