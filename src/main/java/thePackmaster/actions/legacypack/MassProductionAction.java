package thePackmaster.actions.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MassProductionAction extends AbstractGameAction
{
    private AbstractPlayer p;
    private String chooseLabel;

    public MassProductionAction(String label) {
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        chooseLabel = label;
    }

    @Override
    public void update() {
        if (this.duration != Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.unhover();
                    for (final AbstractCard inHand : AbstractDungeon.player.hand.group) {
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(inHand, AbstractDungeon.player.hand));
                        AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }
            this.tickDuration();
            return;
        }
        final CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (final AbstractCard c2 : this.p.drawPile.group) {
                tmp.addToRandomSpot(c2);
        }
        if (tmp.size() == 0) {
            this.isDone = true;
            return;
        }
        if (tmp.size() == 1) {
            final AbstractCard card = tmp.getTopCard();
            card.unhover();
            for (final AbstractCard inHand : AbstractDungeon.player.hand.group) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(inHand, AbstractDungeon.player.hand));
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card.makeStatEquivalentCopy()));
            }
            this.isDone = true;
            return;
        }
        AbstractDungeon.gridSelectScreen.open(tmp, 1, chooseLabel, false);
        this.tickDuration();
    }
}