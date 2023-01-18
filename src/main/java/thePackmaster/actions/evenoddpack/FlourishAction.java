package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlourishAction extends AbstractGameAction {
    
    public FlourishAction(int amount) {
        this.amount = amount;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0) {
            this.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, amount));
        }
        else
        {
            this.addToTop(new GainEnergyAction(1));
        }
        this.isDone = true;
    }
}
