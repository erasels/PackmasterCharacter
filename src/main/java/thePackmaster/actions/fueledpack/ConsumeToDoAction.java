package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.relics.fueledpack.FuelTank;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ConsumeToDoAction extends AbstractGameAction {
    private static final String UI_KEY = "ConsumeUiText";
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractGameAction action;
    private final ArrayList<AbstractCard> suitableCards = new ArrayList<>();

    public ConsumeToDoAction(AbstractGameAction action) {
        this.action = action;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (duration == startDuration) {
            for (AbstractCard c : adp().hand.group) {
                if (adp().hasRelic(FuelTank.ID))
                    suitableCards.add(c);
                else if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS
                        && !c.hasTag(SpireAnniversary5Mod.ASH))
                    suitableCards.add(c);
            }

            if (suitableCards.size() == 0) {
                isDone = true;
                return;
            }

            if (suitableCards.size() == 1) {
                AbstractCard c = suitableCards.get(0);
                finish(c);
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                AbstractCard c = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);
                finish(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void finish(AbstractCard card) {
        att(action);
        att(new ConsumeCardsAction(card));
        isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}