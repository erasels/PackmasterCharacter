package thePackmaster.actions.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.att;


public class FalseGritAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractPlayer p = AbstractDungeon.player;
    private final int timesToGainBlock;
    private int blockGain = 0;

    public FalseGritAction(boolean isAmplified) {
        this.timesToGainBlock = isAmplified ? 2 : 1;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (p.hand.size() == 2) {
                for (AbstractCard c : new ArrayList<>(p.hand.group)) {
                    blockGain += deduceBlockGain(c);
                    p.hand.moveToExhaustPile(c);
                }
                att(new GainBlockAction(p, p, blockGain));
                tickDuration();
                return;
            }

            if (p.hand.size() == 1) {
                att(new GainBlockAction(p, p, blockGain += deduceBlockGain(p.hand.getBottomCard())));
                p.hand.moveToExhaustPile(p.hand.getBottomCard());
                tickDuration();
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 2, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                blockGain += deduceBlockGain(c);
                p.hand.moveToExhaustPile(c);
            }
            att(new GainBlockAction(p, p, blockGain));
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }

    public int deduceBlockGain(AbstractCard c) {
        if (c.costForTurn == -1)
            return EnergyPanel.getCurrentEnergy() * 2 * timesToGainBlock;
        if (c.costForTurn > 0)
            return c.costForTurn * 2 * timesToGainBlock;
        return 0;
    }
}
