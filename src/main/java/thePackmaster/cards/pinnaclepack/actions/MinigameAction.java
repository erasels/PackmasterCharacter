package thePackmaster.cards.pinnaclepack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.CuriosityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MinigameAction extends AbstractGameAction {

    private boolean freeToPlayOnce = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private boolean thisUpgraded = false;

    public MinigameAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, boolean thisUpgraded) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.thisUpgraded = thisUpgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1 && !this.thisUpgraded) {
            effect = (this.energyOnUse*2);
            if (this.p.hasRelic("Chemical X")) {
                effect += 2;
                this.p.getRelic("Chemical X").flash();
            }
        }
        else if (this.thisUpgraded) {
            effect = (this.energyOnUse*2)+2;
            if (this.p.hasRelic("Chemical X")) {
                effect += 2;
                this.p.getRelic("Chemical X").flash();
            }
        }
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                int rng;
                rng = 0;
                rng += AbstractDungeon.cardRandomRng.random(0, 3);
                switch (rng){
                    case 0:
                        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
                        break;
                    case 1:
                        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
                        break;
                    case 2:
                        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, 3), 3));
                        break;
                    case 3:
                        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, 5), 5));
                        break;
                    default:
                        addToBot(new ApplyPowerAction(p, p, new CuriosityPower(p, 69), 69));
                        break;
                }
            }
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }

}