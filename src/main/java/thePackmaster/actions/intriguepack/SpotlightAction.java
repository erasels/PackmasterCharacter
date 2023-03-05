package thePackmaster.actions.intriguepack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.cards.intriguepack.AbstractIntrigueCard;

public class SpotlightAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean upgraded = false;

    public SpotlightAction(int amount, boolean upgraded) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);

            for (AbstractCard card : this.p.drawPile.group)
            {
                if (upgraded)
                {
                    if (AbstractIntrigueCard.isMundane(card) || (card.rarity == AbstractCard.CardRarity.UNCOMMON && card.color != AbstractCard.CardColor.CURSE))
                        tmp.addToRandomSpot(card);
                }
                else
                {
                    if (AbstractIntrigueCard.isMundane(card))
                        tmp.addToRandomSpot(card);
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.drawPile.removeCard(card);
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                    AbstractIntrigueCard.promote(card);
                }

                this.isDone = true;
            } else if (tmp.size() <= this.amount) {
                for(int i = 0; i < tmp.size(); ++i) {
                    AbstractCard card = tmp.getNCardFromTop(i);
                    if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        this.p.drawPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                        AbstractIntrigueCard.promote(card);
                    }
                }

                this.isDone = true;
            } else {
                if (this.amount == 1) {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
                }

                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {

                for( AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                {
                    c.unhover();
                    if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                        this.p.drawPile.moveToDiscardPile(c);
                        this.p.createHandIsFullDialog();
                    } else {
                        this.p.drawPile.removeCard(c);
                        this.p.hand.addToTop(c);
                        AbstractIntrigueCard.promote(c);
                    }

                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
        TEXT = uiStrings.TEXT;
    }
}
