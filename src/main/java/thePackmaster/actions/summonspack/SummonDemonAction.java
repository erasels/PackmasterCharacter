package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.orbs.summonspack.SwarmOfBees;

import static thePackmaster.util.Wiz.*;

public class SummonDemonAction extends AbstractGameAction {
    private static final float startingDuration = Settings.ACTION_DUR_FAST;
    private boolean upgraded;

    public SummonDemonAction(boolean upgraded) {
        actionType = ActionType.POWER;
        duration = startingDuration;
        this.upgraded = upgraded;
    }

    public void update() {
        int totalCost = upgraded ? 1 : 0;
        for (AbstractCard c : adp().hand.group)
            if (c.costForTurn > 0)
                totalCost += c.costForTurn;

        for(int i = 0; i < adp().hand.size(); ++i)
            this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));

        applyToSelf(new StrengthPower(adp(), totalCost));

        isDone = true;
    }
}
