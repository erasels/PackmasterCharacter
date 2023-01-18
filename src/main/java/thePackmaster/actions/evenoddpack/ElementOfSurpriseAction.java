package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import thePackmaster.util.Wiz;

public class ElementOfSurpriseAction extends AbstractGameAction {
    public ElementOfSurpriseAction(int amount) {
        this.amount = amount;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            Wiz.applyToSelfTop(new DuplicationPower(AbstractDungeon.player, amount));
        }
        this.isDone = true;
    }
}
