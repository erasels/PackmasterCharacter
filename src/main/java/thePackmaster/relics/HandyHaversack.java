package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HandyHaversack extends AbstractPackmasterRelic {
    public static final String ID = makeID("HandyHaversack");
    private boolean firstTurn = true;

    public HandyHaversack() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new com.megacrit.cardcrawl.actions.common.DrawCardAction(1));
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            addToBot(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
            this.firstTurn = false;
        }
    }
}
