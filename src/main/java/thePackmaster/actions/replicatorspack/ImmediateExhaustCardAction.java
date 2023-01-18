package thePackmaster.actions.replicatorspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

//thanks Ali
public class ImmediateExhaustCardAction extends AbstractGameAction {

    private final AbstractCard toExhaust;

    public ImmediateExhaustCardAction(AbstractCard toExhaust) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.toExhaust = toExhaust;
    }

    public void update() {
        boolean found = false;
        for (AbstractCard card : AbstractDungeon.player.hand.group)
        {
            if (card == toExhaust) {
                found = true;
                break;
            }
        }
        if (found) {
            AbstractDungeon.player.hand.moveToExhaustPile(toExhaust);
            this.isDone = true;
            return;
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group)
        {
            if (card == toExhaust) {
                found = true;
                break;
            }
        }
        if (found) {
            AbstractDungeon.player.drawPile.moveToExhaustPile(toExhaust);
            this.isDone = true;
            return;
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group)
        {
            if (card == toExhaust) {
                found = true;
                break;
            }
        }
        if (found) {
            AbstractDungeon.player.discardPile.moveToExhaustPile(toExhaust);
            this.isDone = true;
            return;
        }
        //If we havnt found it yet, then its already in the exhaust pile or god knows where, so just do nothing
        this.isDone = true;
    }
}
