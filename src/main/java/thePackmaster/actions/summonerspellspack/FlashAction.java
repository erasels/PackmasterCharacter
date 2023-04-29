package thePackmaster.actions.summonerspellspack;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.summonerspellspack.GhostedPower;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.p;

public class FlashAction extends AbstractGameAction {

    public FlashAction(AbstractCreature source) {
        this.setValues(this.target, source, 1);
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE)
            this.addToTop(new DrawCardAction(this.source, 1));
        else
            AbstractDungeon.player.gainEnergy(1);

        this.isDone = true;
    }
}
