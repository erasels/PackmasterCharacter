package thePackmaster.actions.pixiepack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawSpecificCardAction extends AbstractGameAction {
    AbstractCard toDraw;
    AbstractPlayer player;
    public DrawSpecificCardAction(AbstractCard C)
    {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        toDraw = C;
    }
    @Override
    public void update() {
        if (!this.player.drawPile.isEmpty() || !this.player.hand.contains(toDraw))
        {
            if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                this.player.drawPile.moveToDiscardPile(toDraw);
                this.player.createHandIsFullDialog();
            } else {
                this.player.drawPile.moveToHand(toDraw, this.player.drawPile);
            }
        }
        isDone = true;
    }
}
