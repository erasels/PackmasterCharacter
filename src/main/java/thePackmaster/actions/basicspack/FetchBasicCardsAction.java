package thePackmaster.actions.basicspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FetchBasicCardsAction extends AbstractGameAction {
    private AbstractPlayer p;

    public FetchBasicCardsAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues((AbstractCreature)this.p, (AbstractCreature)this.p, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            CardGroup basics = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.drawPile.group) {
                if (AbstractCard.CardRarity.BASIC == c.rarity)
                    basics.addToRandomSpot(c);
            }
            if (basics.size() == 0) {
                this.isDone = true;
                return;
            }
            for (int i = 0; i < this.amount; i++) {
                if (!basics.isEmpty()) {
                    basics.shuffle();
                    AbstractCard card = basics.getBottomCard();
                    basics.removeCard(card);
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        this.p.drawPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                }
            }
            this.isDone = true;
        }
        tickDuration();
    }
}
