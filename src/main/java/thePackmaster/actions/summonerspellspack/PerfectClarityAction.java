package thePackmaster.actions.summonerspellspack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;

public class PerfectClarityAction extends AbstractGameAction {

    public PerfectClarityAction() {
        this.actionType = ActionType.ENERGY;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST)
            Wiz.p().gainEnergy(Wiz.p().hand.size());

        this.tickDuration();
    }
}