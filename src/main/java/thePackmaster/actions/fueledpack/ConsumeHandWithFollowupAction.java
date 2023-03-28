package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.relics.fueledpack.FuelTank;

import java.util.ArrayList;
import java.util.function.Consumer;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ConsumeHandWithFollowupAction extends AbstractGameAction {
    private static final String UI_KEY = "ConsumeUiText";
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> suitableCards = new ArrayList<>();
    private final Consumer<Integer> consumer;

    public ConsumeHandWithFollowupAction(Consumer<Integer> consumer) {
        this.consumer = consumer;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.DAMAGE;
    }

    public void update() {
        isDone = true;

        for (AbstractCard c : adp().hand.group) {
            if (adp().hasRelic(FuelTank.ID))
                suitableCards.add(c);
            else if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS
                    && !c.hasTag(SpireAnniversary5Mod.ASH))
                suitableCards.add(c);
        }

        if (suitableCards.size() == 0)
            return;

        consumer.accept(suitableCards.size());
        att(new ConsumeCardsAction(suitableCards));
        isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}