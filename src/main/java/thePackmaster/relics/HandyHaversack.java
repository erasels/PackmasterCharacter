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
            int count = AbstractDungeon.player.masterDeck.size();
            if (count >= 10) {
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToTop(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
            }
            if (count >= 20) {
                addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(2));
            }
            this.firstTurn = false;
        }
    }
}
