package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static thePackmaster.util.Wiz.*;

public class DemonicRitualAction extends AbstractGameAction {
    private static final float startingDuration = Settings.ACTION_DUR_FAST;
    private final int bonus;

    public DemonicRitualAction(int bonus) {
        actionType = ActionType.POWER;
        duration = startingDuration;
        this.bonus = bonus;
    }

    public void update() {
        int totalCost = bonus;
        for (AbstractCard c : adp().hand.group)
            if (c.costForTurn > 0)
                totalCost += c.costForTurn;

        for(int i = 0; i < adp().hand.size(); ++i)
            this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));

        if (totalCost > 0)
            applyToSelf(new StrengthPower(adp(), totalCost));

        isDone = true;
    }
}
