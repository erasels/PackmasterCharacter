package thePackmaster.actions.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.rippack.AbstractRippableCard;

import static thePackmaster.util.Wiz.att;

public class RipCardAction extends AbstractGameAction {
    private AbstractRippableCard rippedCard;
    private AbstractCard artCard;
    private AbstractCard textCard;

    public RipCardAction(AbstractRippableCard rippedCard, AbstractCard artCard, AbstractCard textCard) {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
        this.rippedCard = rippedCard;
        this.artCard = artCard;
        this.textCard = textCard;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean found = false;
        for (AbstractCard card : p.hand.group) {
            if (card == rippedCard) {
                found = true;
                break;
            }
        }
        if(found && rippedCard != null) {
            artCard.applyPowers();
            textCard.applyPowers();
            artCard.cost = rippedCard.cost;
            artCard.costForTurn = rippedCard.costForTurn; //costs need to be updated if the base card's cost gets updated
            if (AbstractDungeon.player.hoveredCard == rippedCard) {
                AbstractDungeon.player.releaseCard();
            }
            AbstractDungeon.actionManager.cardQueue.removeIf(q -> q.card == rippedCard);

            att(new MakeTempCardInHandAction(textCard));
            att(new MakeTempCardInHandAction(artCard));
            rippedCard.onRip();
            AbstractDungeon.player.hand.removeCard(rippedCard);
            p.hand.applyPowers();
            p.hand.glowCheck();
            artCard.superFlash();
            textCard.superFlash();
        }
        isDone = true;
    }
}
