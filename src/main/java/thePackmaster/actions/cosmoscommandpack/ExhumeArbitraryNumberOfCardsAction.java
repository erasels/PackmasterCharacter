package thePackmaster.actions.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class ExhumeArbitraryNumberOfCardsAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(
            makeID("ExhumeArbitraryNumberOfCardsAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    public CardGroup exhaustPileCopy;

    public ExhumeArbitraryNumberOfCardsAction(int amount) {
        this.p = adp();
        this.setValues(p, adp(), amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (this.p.exhaustPile.isEmpty())
                this.isDone = true;
            else if (p.exhaustPile.size() <= amount) {
                exhaustPileCopy = new CardGroup(p.exhaustPile, CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : exhaustPileCopy.group)
                    ExhumeCard(c);
                this.isDone = true;
            } else {
                for (AbstractCard c : p.exhaustPile.group) {
                    c.stopGlowing();
                    c.unhover();
                    c.unfadeOut();
                }

                exhaustPileCopy = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : p.exhaustPile.group)
                    if (!c.cardID.equals("Exhume"))
                        exhaustPileCopy.addToBottom(c);

                if (exhaustPileCopy.isEmpty())
                    isDone = true;
                else {
                    AbstractDungeon.gridSelectScreen.open(exhaustPileCopy, amount,
                            amount > 1 ? TEXT[1] + amount + TEXT[2] : TEXT[0],
                            false);
                    this.tickDuration();
                }
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                   ExhumeCard(c);

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();

                for (AbstractCard c : exhaustPileCopy.group) {
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }

                exhaustPileCopy.clear();
            }
            this.tickDuration();
        }
    }

    public void ExhumeCard(AbstractCard c) {
        if (c.cardID.equals("Exhume"))
            return;
        c.unfadeOut();
        p.hand.addToHand(c);
        if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL)
            c.setCostForTurn(-9);
        p.exhaustPile.removeCard(c);
        c.unhover();
        c.fadingOut = false;
    }
}
