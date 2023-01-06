package thePackmaster.actions.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SharpeningFollowUpAction extends AbstractGameAction {

    public SharpeningFollowUpAction() {
        duration = 0.01f;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));// 18
        this.tickDuration();// 20
        if (this.isDone) {// 22
            addToBot(new SpecificUpgradeWithVfxAction(DrawCardAction.drawnCards.toArray(new AbstractCard[0])));
        }
    }

}
