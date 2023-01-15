package thePackmaster.actions.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayRandomCardAction extends AbstractGameAction
{
    private final CardGroup group;
    public PlayRandomCardAction(AbstractCreature target, CardGroup group, int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.amount = amount;
        this.group = group;
    }

    public void update() {
        if (group.size() == 0) {
            this.isDone = true;
            return;
        }

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.group.addAll(group.group);

        for (int i = 0; i < Math.min(tmp.size(), amount); i++)
        {
            AbstractCard c = tmp.getRandomCard(AbstractDungeon.cardRng);
            if (c != null)
            {
                group.group.remove(c);
                AbstractDungeon.getCurrRoom().souls.remove(c);
                this.addToBot(new NewQueueCardAction(c, target, false, true));
            }
        }


        this.isDone = true;
    }
}
