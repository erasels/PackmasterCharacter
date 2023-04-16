package thePackmaster.actions.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

import java.util.concurrent.atomic.AtomicInteger;

public class CountSelfDebuffsAction extends AbstractGameAction {

    private AtomicInteger count = new AtomicInteger(0);

    public CountSelfDebuffsAction(){
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
        update();
    }

    public void update(){
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().powers.stream()
                        .filter(p -> p.type == AbstractPower.PowerType.DEBUFF)
                        .forEach(p -> count.getAndIncrement());
                isDone = true;
            }
        });
    }
}
