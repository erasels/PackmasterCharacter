package thePackmaster.actions.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static thePackmaster.util.Wiz.*;

public class ActivateGlaciateAction extends AbstractGameAction {
    AbstractPower glaciate;

    public ActivateGlaciateAction(AbstractPower glaciateInstance) {
        this.glaciate = glaciateInstance;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        glaciate.flash();
        if (Settings.FAST_MODE)
            addToBot(new WaitAction(0.1F));
        else
            addToBot(new WaitAction(0.2F));
        applyToSelf(new VigorPower(AbstractDungeon.player, glaciate.amount));
        this.isDone = true;
    }
}