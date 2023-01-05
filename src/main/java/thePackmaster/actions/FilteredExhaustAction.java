package thePackmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilteredExhaustAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    private Predicate<AbstractCard> condition;


    public FilteredExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public FilteredExhaustAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }

    public FilteredExhaustAction(int amount, boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }

    public FilteredExhaustAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public FilteredExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.duration = this.startDuration = duration;
    }

    public FilteredExhaustAction filter(Predicate<AbstractCard> condition) {
        this.condition = condition;
        return this;
    }

    private List<AbstractCard> filtered = new ArrayList<>();

    public void update() {
        if (duration == startDuration) {
            if (condition != null) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (!condition.test(c))
                        filtered.add(c);
                }
            }

            if (p.hand.size() - filtered.size() <= 0) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && p.hand.size() - filtered.size() <= this.amount) {
                this.amount = p.hand.size() - filtered.size();

                List<AbstractCard> toExhaust = new ArrayList<>(p.hand.group);
                toExhaust.removeAll(filtered);

                for (AbstractCard c : toExhaust) {
                    p.hand.moveToExhaustPile(c);
                }

                CardCrawlGame.dungeon.checkForPactAchievement();
                return;
            }

            if (!this.isRandom) {
                p.hand.group.removeAll(filtered);
                if (p.hand.isEmpty()) {
                    p.hand.group.addAll(filtered);
                    tickDuration();
                    return;
                }

                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }

            List<AbstractCard> toExhaust = new ArrayList<>(p.hand.group);
            toExhaust.removeAll(filtered);
            for (int i = 0; i < this.amount; ++i) {
                if (toExhaust.isEmpty())
                    break;
                p.hand.moveToExhaustPile(toExhaust.remove(AbstractDungeon.cardRandomRng.random(toExhaust.size() - 1)));
            }

            CardCrawlGame.dungeon.checkForPactAchievement();
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToExhaustPile(c);
            }

            CardCrawlGame.dungeon.checkForPactAchievement();
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }

        this.tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.filtered) {
            p.hand.addToTop(c);
        }

        p.hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }
}
