package thePackmaster.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawToHandAction extends AbstractGameAction {
    private AbstractCard card;

    public DrawToHandAction(AbstractCard card) {
        actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.contains(card) && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.hand.addToHand(card);
                card.unhover();
                card.setAngle(0f, true);
                card.lighten(false);
                card.drawScale = Settings.CARD_SOUL_SCALE;
                card.targetDrawScale = Settings.CARD_VIEW_SCALE;
                card.applyPowers();
                AbstractDungeon.player.drawPile.removeCard(card);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
        }

        isDone = true;
    }
}
