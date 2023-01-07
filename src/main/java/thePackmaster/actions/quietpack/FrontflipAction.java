package thePackmaster.actions.quietpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.util.Wiz.adp;

public class FrontflipAction extends AbstractGameAction {

    public FrontflipAction() {
        this.duration = 0.001F;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(3.0F));
        this.tickDuration();

        {
            Iterator var1 = DrawCardAction.drawnCards.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (c.cost == -2) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
        }

        isDone = true;
    }

}
