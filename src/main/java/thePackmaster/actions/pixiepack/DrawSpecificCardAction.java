package thePackmaster.actions.pixiepack;

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
        this.actionType = ActionType.CARD_MANIPULATION;// 21
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        toDraw = C;
    }
    @Override
    public void update() {
        if (!this.player.drawPile.isEmpty() || !this.player.hand.contains(toDraw))
        {
            if (this.player.hand.size() == 10) {// 43
                this.player.drawPile.moveToDiscardPile(toDraw);// 44
                this.player.createHandIsFullDialog();// 45
            } else {
                this.player.drawPile.moveToHand(toDraw, this.player.drawPile);// 47
            }
        }
        isDone = true;
    }
}
