package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

public class BattleCryAction extends AbstractGameAction {
    
    public BattleCryAction(int amount) {
        this.amount = amount;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            Wiz.applyToSelf(new StrengthPower(AbstractDungeon.player, amount));
        }
        this.addToTop(new DrawCardAction(amount));
        this.isDone = true;
    }
}
