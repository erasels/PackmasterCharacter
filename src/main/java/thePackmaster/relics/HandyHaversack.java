package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HandyHaversack extends AbstractPackmasterRelic {
    public static final String ID = makeID("HandyHaversack");
    private boolean firstTurn = true;

    public HandyHaversack() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT);
    }


    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            int count = AbstractDungeon.player.drawPile.size();
            if (count >= 10){
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
            for (int i = 0; i < 3; i++) {
                if (count >= 10) {
                    count -=10;
                    addToTop(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
                }
            }
            this.firstTurn = false;
        }
    }
}
