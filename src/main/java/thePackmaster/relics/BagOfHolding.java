package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.ThePackmaster;

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
        if (this.firstTurn) {
            flash();
            int count = AbstractDungeon.player.drawPile.size();
            if (count >= 10) {
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
            for (int i = 0; i < 3; i++) {
                if (count >= 10) {
                    count -= 10;
                    addToTop(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
                    addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(1));
                }
            }
            this.firstTurn = false;
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

