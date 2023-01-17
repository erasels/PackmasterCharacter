package thePackmaster.actions.anomalypack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;

import java.util.Iterator;

public class ThoughtweavingAction extends AbstractGameAction {
    private AbstractPlayer p;
    private CardType typeToCheck;
    private AbstractCardPack packToAvoid;

    public ThoughtweavingAction(int amount, CardType type, AbstractCardPack packToAvoid) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.typeToCheck = type;
        this.packToAvoid = packToAvoid;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            int counter = 0;
            AbstractPackmasterCard c;
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            Iterator var2 = this.p.drawPile.group.iterator();

            AbstractCard card;
            while(var2.hasNext() && counter < amount) {
                AbstractCard check = (AbstractCard)var2.next();
                if (check instanceof AbstractPackmasterCard) {
                    c = (AbstractPackmasterCard) check;
                    if (c.type == this.typeToCheck && c.getParent() != packToAvoid && c.getParent()!=null) {
                        tmp.addToRandomSpot(c);
                        counter++;
                    }
                }
            }

            if (tmp.isEmpty()) {
                this.isDone = true;
                return;
            }


            for(int i = 0; i < counter; ++i) {
                if (!tmp.isEmpty()) {
                    if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                        this.p.createHandIsFullDialog();
                    } else {
                        card = tmp.getBottomCard();
                        tmp.removeCard(card);
                        p.drawPile.group.remove(card);
                        p.drawPile.addToTop(card);
                        this.addToBot(new DrawCardAction(1));
                    }
                }
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
