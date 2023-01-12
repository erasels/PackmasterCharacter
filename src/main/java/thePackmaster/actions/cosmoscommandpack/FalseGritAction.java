package thePackmaster.actions.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.att;


public class FalseGritAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractPlayer p = AbstractDungeon.player;
    private final int blockPerCard;
    private final int cards;
    private final AbstractCard caller;

    public FalseGritAction(int blockPerCard, int cardsToExhaust, AbstractCard callingCard) {
        this.blockPerCard = blockPerCard;
        this.cards = cardsToExhaust;
        this.caller = callingCard;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (p.hand.size() <= cards) {
                for (AbstractCard c : new ArrayList<>(p.hand.group))
                    if (c != caller) {
                        p.hand.moveToExhaustPile(c);
                        att(new GainBlockAction(p, p, blockPerCard));
                    }
                tickDuration();
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], cards, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToExhaustPile(c);
                att(new GainBlockAction(p, p, blockPerCard));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }
}
