package thePackmaster.actions.ringofpainpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class HollowerAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;

    public HollowerAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        AbstractCard card;
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);

            for (AbstractCard abstractCard : this.p.drawPile.group) {
                card = abstractCard;
                tmp.addToTop(card);
            }

            tmp.sortAlphabetically(true);
            tmp.sortByRarityPlusStatusCardType(false);

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() <= amount) {
                for (AbstractCard abstractCard : tmp.group) {
                    this.p.drawPile.moveToExhaustPile(abstractCard);
                }
                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (AbstractCard abstractCard : AbstractDungeon.gridSelectScreen.selectedCards) {
                    card = abstractCard;
                    card.unhover();
                    this.p.drawPile.moveToExhaustPile(card);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }
}
