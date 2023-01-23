package thePackmaster.actions.psychicpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.patches.psychicpack.occult.OccultFields;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MakeCardInHandOccultAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("RuleCancel"));
    public static final String[] TEXT = uiStrings.TEXT;

    private final AbstractPlayer p;
    private final boolean random;
    private ArrayList<AbstractCard> invalidCards;
    private boolean firstFrame = true;

    public MakeCardInHandOccultAction(boolean random)
    {
        p = AbstractDungeon.player;
        this.random = random;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = 0.1f;

        invalidCards = new ArrayList<>();
    }

    @Override
    public void update() {
        if (firstFrame) {
            firstFrame = false;

            CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            if (p.hand.isEmpty())
            {
                this.isDone = true;
                return;
            }

            for (AbstractCard c : p.hand.group) {
                if (!OccultFields.isOccult.get(c))
                    validCards.addToTop(c);
            }

            if (validCards.isEmpty())
            {
                this.isDone = true;
                return;
            }

            if (validCards.size() == 1)
            {
                OccultFields.isOccult.set(validCards.getTopCard(), true);
                validCards.getTopCard().initializeDescription();
                validCards.getTopCard().superFlash(Color.VIOLET.cpy());
                addToTop(new HandCheckAction());
                this.isDone = true;
                return;
            }

            if (random) {
                AbstractCard nowOccult = validCards.getRandomCard(AbstractDungeon.cardRandomRng);
                OccultFields.isOccult.set(nowOccult, true);
                nowOccult.initializeDescription();
                nowOccult.superFlash(Color.VIOLET.cpy());
                addToTop(new HandCheckAction());
                this.isDone = true;
                return;
            }

            invalidCards.addAll(p.hand.group);
            invalidCards.removeIf(validCards::contains);
            p.hand.group.removeAll(invalidCards);

            if (p.hand.isEmpty())
            {
                returnCards();
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.addToTop(c);
                OccultFields.isOccult.set(c, true);
                c.superFlash(Color.VIOLET.cpy());
                c.initializeDescription();
            }
            addToTop(new HandCheckAction());

            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.invalidCards) {
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }
}
