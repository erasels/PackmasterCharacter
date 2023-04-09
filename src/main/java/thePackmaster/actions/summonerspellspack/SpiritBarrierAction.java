package thePackmaster.actions.summonerspellspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;

public class SpiritBarrierAction extends AbstractGameAction {

    public SpiritBarrierAction() {
        this.actionType = ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (!Wiz.p().hand.isEmpty())
            this.addToTop(new AddTemporaryHPAction(Wiz.p(), Wiz.p(), Wiz.p().hand.group.size()));

        this.isDone = true;
    }
}