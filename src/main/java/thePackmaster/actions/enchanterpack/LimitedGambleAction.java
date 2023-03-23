package thePackmaster.actions.enchanterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class LimitedGambleAction extends AbstractGameAction {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("LimitedGambleAction"));

    private int amount;

    public LimitedGambleAction(int amount) {
        this.amount = amount;
        duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            if (Wiz.hand().size() == 0) {
                isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(strings.TEXT[0],amount, true, true);
                tickDuration();
            }
        } else {
            ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            for (AbstractCard c : cards) {
                AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            addToTop(new DrawCardAction(cards.size()));
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            isDone = true;
        }
    }
}
