package thePackmaster.actions.psychicpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.patches.psychicpack.occult.OccultFields;

import java.util.ArrayList;
import java.util.function.Predicate;

public class RandomOccultCardsAction extends AbstractGameAction {
    private final Predicate<AbstractCard> condition;
    private final CardGroup targetGroup;

    public RandomOccultCardsAction(int amount)
    {
        this(amount, (c)->true);
    }
    public RandomOccultCardsAction(int amount, Predicate<AbstractCard> condition)
    {
        this(AbstractDungeon.player.hand, amount, condition);
    }
    public RandomOccultCardsAction(CardGroup targetGroup, int amount)
    {
        this(AbstractDungeon.player.hand, amount, (c)->true);
    }
    public RandomOccultCardsAction(CardGroup targetGroup, int amount, Predicate<AbstractCard> condition)
    {
        this.targetGroup = targetGroup;
        this.condition = condition;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (targetGroup.isEmpty())
        {
            this.isDone = true;
            return;
        }

        ArrayList<AbstractCard> validCards = new ArrayList<>(targetGroup.group);

        validCards.removeIf((c)->!condition.test(c) || OccultFields.isOccult.get(c));

        if (validCards.isEmpty())
        {
            this.isDone = true;
            return;
        }

        if (validCards.size() <= this.amount)
        {
            for (AbstractCard c : validCards)
            {
                OccultFields.isOccult.set(c, true);
                if (targetGroup.equals(AbstractDungeon.player.hand))
                    c.superFlash(Color.VIOLET.cpy());
                c.initializeDescription();
            }
        }
        else
        {
            for (int i = 0; i < this.amount; ++i)
            {
                if (validCards.isEmpty())
                    break;

                AbstractCard c = validCards.remove(AbstractDungeon.cardRandomRng.random(validCards.size() - 1));

                OccultFields.isOccult.set(c, true);
                c.superFlash(Color.VIOLET.cpy());
                c.initializeDescription();
            }
        }

        if (targetGroup.equals(AbstractDungeon.player.hand))
            addToTop(new HandCheckAction());

        this.isDone = true;
    }
}
