package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;
import thePackmaster.ThePackmaster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BagOfHolding extends AbstractPackmasterRelic {
    public static final String ID = makeID("BagOfHolding");

    private boolean firstTurn = true;

    public BagOfHolding() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void atTurnStart() {
        int count = AbstractDungeon.player.masterDeck.size();
        if (this.firstTurn) {
            flash();
            if (count >= 10){
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToTop(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
            }
            if (count >= 20){
                addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(2));
            }
            this.firstTurn = false;
        } else {
            if (count >= 30){
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToTop(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
            }
            if (count >= 40){
                addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(2));
            }
        }
    }
    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(HandyHaversack.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(HandyHaversack.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HandyHaversack.ID);
    }

}

