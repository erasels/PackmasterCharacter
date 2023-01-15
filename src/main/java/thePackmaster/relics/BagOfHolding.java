package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BagOfHolding extends AbstractPackmasterRelic {
    public static final String ID = makeID("BagOfHolding");

    public BagOfHolding() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.counter = AbstractDungeon.player.masterDeck.size() / 5;
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            flash();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToTop(new GainEnergyAction(1));
            this.counter--;

            if (this.counter <= 0) {
                this.counter = -1;
                this.grayscale = true;
            }
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
        this.counter = -1;
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

