package thePackmaster.actions.entropypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FastDiscardAction extends AbstractGameAction {
    private AbstractCard card = null;

    public FastDiscardAction(int amt) {
        this.actionType = ActionType.DISCARD;
        this.amount = amt;
    }
    public FastDiscardAction(AbstractCard c) {
        this.actionType = ActionType.DISCARD;
        this.card = c;
    }

    @Override
    public void update() {
        this.isDone = true;

        if (card != null) {
            if (AbstractDungeon.player.hand.contains(card)) {
                AbstractDungeon.player.hand.moveToDiscardPile(card);
                card.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            return;
        }

        for(int i = 0; i < this.amount; ++i) {
            if (AbstractDungeon.player.hand.isEmpty())
                break;

            card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            AbstractDungeon.player.hand.moveToDiscardPile(card);
            card.triggerOnManualDiscard();
            GameActionManager.incrementDiscard(false);
        }
        this.isDone = true;
    }
}
