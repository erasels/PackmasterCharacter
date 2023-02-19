package thePackmaster.actions.marisapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class ExhaustForChargeAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ExhaustForChargeAction(int amount) {
        this.amount = amount;
        startDuration = duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (Wiz.hand().isEmpty()) {
                isDone = true;
                return;
            }
            if (Wiz.hand().size() <= amount) {
                exhaustAndGain(Wiz.hand().group);
                tickDuration();
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            exhaustAndGain(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }

    private void exhaustAndGain(ArrayList<AbstractCard> list) {
        int totalCost = 0;
        list = new ArrayList<>(list);
        for (AbstractCard c : list) {
            int cost = Wiz.getLogicalCardCost(c);
            if (c instanceof AmplifyCard) {
                cost += ((AmplifyCard) c).getAmplifyCost();
            }
            totalCost += cost * 2;
            Wiz.hand().moveToExhaustPile(c);
        }
        if(totalCost > 0)
            Wiz.applyToSelf(new ChargeUpPower(totalCost));
    }
}
