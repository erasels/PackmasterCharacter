package thePackmaster.actions.gowiththeflowpack;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import thePackmaster.powers.gowiththeflowpack.FlowPower;

public class FlowDrawAction extends AbstractGameAction {
    private static final float DURATION = Settings.ACTION_DUR_FAST;
    private final FlowPower power;

    public FlowDrawAction(FlowPower power) {
        duration = DURATION;
        this.power = power;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
            AbstractDungeon.player.getPower(NoDrawPower.POWER_ID).flash();
            endAction();
            return;
        }

        if (power.amount <= 0) {
            endAction();
            return;
        }

        int deckSize = AbstractDungeon.player.drawPile.size();
        int discardSize = AbstractDungeon.player.discardPile.size();

        if (SoulGroup.isActive()) {
            return;
        }

        if (deckSize + discardSize == 0) {
            endAction();
            return;
        }

        if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
            AbstractDungeon.player.createHandIsFullDialog();
            endAction();
            return;
        }

        // If deck is empty, immediately end this action, shuffle, then start a new draw action
        if (deckSize == 0 && power.amount > 0) {
            addToTop(new FlowDrawAction(power));
            addToTop(new EmptyDeckShuffleAction());
            isDone = true;
            return;
        }

        duration -= Gdx.graphics.getDeltaTime();

        if (power.amount != 0 && duration < 0f) {
            if (Settings.FAST_MODE) {
                duration = Settings.ACTION_DUR_XFAST;
            } else {
                duration = Settings.ACTION_DUR_FASTER;
            }

            if (!AbstractDungeon.player.drawPile.isEmpty()) {
                power.reducePower(1);
                AbstractDungeon.player.draw();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            if (power.amount == 0) {
                endAction();
            }
        }
    }

    private void endAction() {
        isDone = true;
        addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }
}