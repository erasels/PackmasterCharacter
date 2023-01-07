package thePackmaster.actions.ringofpainpack;

import basemod.BaseMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HollowerDiscardPileToHandAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;

    public HollowerDiscardPileToHandAction(int numberOfCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                if (this.numberOfCards == 1) {
                    AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                }
                this.tickDuration();
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (this.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        this.player.hand.addToHand(c);
                        CardModifierManager.addModifier(c, new ExhaustMod());
                        this.player.discardPile.removeCard(c);
                    }
                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for (AbstractCard c : this.player.discardPile.group) {
                    c.target_y = 0.0F;
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                for (AbstractCard c : this.player.hand.group) {
                    c.applyPowers();
                }
            }

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
