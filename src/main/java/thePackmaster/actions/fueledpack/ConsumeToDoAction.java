package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.lwjgl.Sys;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fueledpack.HotAsh;
import thePackmaster.relics.fueledpack.FuelTank;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ConsumeToDoAction extends AbstractGameAction {
    private static final String UI_KEY = SpireAnniversary5Mod.makeID("ConsumeUiText");
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractGameAction action;
    private final ArrayList<AbstractCard> unsuitableCards = new ArrayList<>();

    public ConsumeToDoAction(AbstractGameAction action) {
        this.action = action;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (duration == startDuration) {
            for (AbstractCard c : adp().hand.group) {
                if (!adp().hasRelic(FuelTank.ID) && (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS
                        || c instanceof HotAsh))
                    unsuitableCards.add(c);
            }

            adp().hand.group.removeAll(unsuitableCards);

            if (adp().hand.size() == 0) {
                isDone = true;
                finish(null);
                return;
            }

            if (adp().hand.size() == 1) {
                AbstractCard c = adp().hand.getTopCard();
                adp().releaseCard();
                adp().hand.stopGlowing();
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
        for (AbstractCard c : unsuitableCards)
            adp().hand.addToTop(c);

        adp().hand.refreshHandLayout();
        if (card != null) {
            att(action);
            att(new ConsumeCardsAction(card));
        }
        isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}