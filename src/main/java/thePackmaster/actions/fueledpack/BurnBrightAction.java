package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fueledpack.HotAsh;
import thePackmaster.powers.fueledpack.BurnBrightPower;
import thePackmaster.relics.fueledpack.FuelTank;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.*;

public class BurnBrightAction extends AbstractGameAction {
    private static final String UI_KEY = SpireAnniversary5Mod.makeID("ConsumeUiText");
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> unsuitableCards = new ArrayList<>();
    private final int upgraded;

    public BurnBrightAction(int effect, int upgraded) {
        amount = effect;
        this.upgraded = upgraded;
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
                finish(null, upgraded);
                return;
            }

            if (adp().hand.size() <= amount) {
                finish(adp().hand.group, adp().hand.size() + upgraded);
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                finish(AbstractDungeon.handCardSelectScreen.selectedCards.group, amount + upgraded);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void finish(ArrayList<AbstractCard> cards, int amount) {
        if (amount > 0)
            applyToSelfTop(new BurnBrightPower(amount));

        if (!cards.isEmpty())
            att(new ConsumeCardsAction(cards));

        for (AbstractCard c : unsuitableCards)
            adp().hand.addToTop(c);

        adp().hand.refreshHandLayout();

        isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}