package thePackmaster.actions.summonerspellspack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlashAction extends AbstractGameAction {

    public FlashAction(AbstractCreature source) {
        this.setValues(this.target, source, 1);
        this.actionType = ActionType.DRAW;
    }

    public void update() {
        if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE)
            this.addToTop(new DrawCardAction(this.source, 1));
        else
            AbstractDungeon.player.gainEnergy(1);

        this.isDone = true;
    }
}
