package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.boardgamepack.DicePower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.util.Wiz.adp;

public class DelayedDiceLevelUpAction extends AbstractGameAction {

    public DelayedDiceLevelUpAction() {
    }
    public void update() {

        addToBot(new ApplyPowerAction(adp(), adp(), new DicePower(adp(), 6), 6));
        addToBot(new ApplyPowerAction(adp(), adp(), new DicePower(adp(), 6), 6));
        this.isDone = true;
    }
}
