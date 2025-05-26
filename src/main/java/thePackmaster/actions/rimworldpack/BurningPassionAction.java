package thePackmaster.actions.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class BurningPassionAction extends AbstractGameAction {
    private float startingDuration;

    private boolean canPick;

    public BurningPassionAction(boolean canPick) {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.canPick = canPick;
    }// 22

    public void update() {
        if(canPick){
            if(duration == startingDuration){
                if(AbstractDungeon.player.hand.size() == 0)
                {
                    isDone = true;
                    return;
                }
                AbstractDungeon.handCardSelectScreen.open(ExhaustAction.TEXT[0], 999, true, true);
                tickDuration();
                return;
            }

            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 115
                for (AbstractCard c: AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToExhaustPile(c);
                }
                addToTop(new DrawCardAction(AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
                addToTop(new GainEnergyAction(AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
                CardCrawlGame.dungeon.checkForPactAchievement();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
            this.tickDuration();
        }
        else
        {
            if(AbstractDungeon.player.hand.size() == 0)
            {
                isDone = true;
                return;
            }
            int numCards = AbstractDungeon.player.hand.size();
            addToTop(new DrawCardAction(numCards));
            addToTop(new GainEnergyAction(numCards));
            for(int i = 0; i < numCards; ++i) {
                if (Settings.FAST_MODE)
                    addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
                else
                    addToTop(new ExhaustAction(1, true, true));
            }
            isDone = true;
        }
    }
}