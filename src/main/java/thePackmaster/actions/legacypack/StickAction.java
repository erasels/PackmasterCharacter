package thePackmaster.actions.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.legacypack.StickStrike;

public class StickAction extends AbstractGameAction
{

    public AbstractCard card = null;
    public boolean canBeAnyCard = false;

    public StickAction(final int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (final AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof StickStrike) {
                    c.flash();
                    c.baseDamage += this.amount;
                    c.applyPowers();
                    if (c == StickStrike.lastPlayed){
                        // last-played card is already in hand, pull another one instead
                        canBeAnyCard = true;
                    }
                }
            }
            for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof StickStrike) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                    if (c == StickStrike.lastPlayed || (canBeAnyCard && this.card == null)){
                        this.card = c;
                        AbstractDungeon.actionManager.addToTop(new DiscardToHandAction(card));
                    }
                }
            }
            for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c instanceof StickStrike) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                    if (c == StickStrike.lastPlayed || (canBeAnyCard && this.card == null)){
                        this.card = c;
                        AbstractDungeon.actionManager.addToTop(new FetchCardToHandAction(card, AbstractDungeon.player.drawPile));
                    }
                }
            }
            for (final AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c instanceof StickStrike) {
                    c.baseDamage += this.amount;
                    c.applyPowers();
                    if (c == StickStrike.lastPlayed || (canBeAnyCard && this.card == null)){
                        this.card = c;
                        AbstractDungeon.actionManager.addToTop(new FetchCardToHandAction(card, AbstractDungeon.player.exhaustPile));
                    }
                }
            }
        }
        this.tickDuration();
    }
}