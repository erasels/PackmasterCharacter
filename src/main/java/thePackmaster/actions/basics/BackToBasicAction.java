package thePackmaster.actions.basics;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cardmodifiers.basicspack.DuplicateModifier;

import java.util.ArrayList;

public class BackToBasicAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;

    private AbstractPlayer player;
    private AbstractCard sourceCard;

    public BackToBasicAction(AbstractCard sourceCard) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.sourceCard = sourceCard;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (this.player.discardPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (this.player.discardPile.size() == 1) {
                ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
                for (AbstractCard c : this.player.discardPile.group)
                    cardsToMove.add(c);
                for (AbstractCard c : cardsToMove) {
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        this.player.discardPile.removeCard(c);
                    }
                    c.lighten(false);
                    c.applyPowers();
                }
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, 1, true, TEXT[0]);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (this.player.hand.size() < 10) {
                    this.player.hand.addToHand(c);
                    this.player.discardPile.removeCard(c);
                }
                c.lighten(false);
                c.unhover();
                c.applyPowers();
                if(c.rarity == AbstractCard.CardRarity.BASIC){
                    this.player.hand.removeCard(this.sourceCard);
                }
            }
            for (AbstractCard c : this.player.discardPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
        if (this.isDone)
            for (AbstractCard c : this.player.hand.group)
                c.applyPowers();
    }
}
