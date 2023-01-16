package thePackmaster.actions.anomalypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import thePackmaster.cards.anomalypack.GoldenGun;

import java.util.Iterator;

public class GoldenRoundAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard c;
    private CardType typeToCheck;

    public GoldenRoundAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.c = c;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.typeToCheck = CardType.CURSE;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            int counter = 0;
            boolean done =false;
            GoldenGun gg;
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            Iterator var2 = this.p.hand.group.iterator();

            AbstractCard card;
            while(var2.hasNext() && !done) {

                card = (AbstractCard)var2.next();
                if (card instanceof GoldenGun) {
                    gg =(GoldenGun) card;
                    if (!gg.loaded()) {
                        done=true;
                        gg.load();
                    }
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }


            if (!done) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, c.current_x, c.current_y, false));
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
