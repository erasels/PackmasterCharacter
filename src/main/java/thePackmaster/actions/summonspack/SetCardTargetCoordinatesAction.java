package thePackmaster.actions.summonspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SetCardTargetCoordinatesAction extends AbstractGameAction {
    private final AbstractCard card;
    private final float destinationX;
    private final float destinationY;

    public SetCardTargetCoordinatesAction(AbstractCard card, float destinationX, float destinationY) {
        this.actionType = ActionType.SPECIAL;
        this.card = card;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    @Override
    public void update() {
        if (destinationX != -1.0f)
            card.target_x = destinationX;
        if (destinationY != -1.0f)
            card.target_y = destinationY;
        isDone = true;
    }
}