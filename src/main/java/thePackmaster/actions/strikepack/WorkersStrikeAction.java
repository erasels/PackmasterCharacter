package thePackmaster.actions.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

public class WorkersStrikeAction extends AbstractGameAction {


    public WorkersStrikeAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        ArrayList<AbstractCard> potentialTargets = new ArrayList<>(AbstractDungeon.player.drawPile.group);
        ArrayList<AbstractCard> validTargets = new ArrayList<>();

        boolean hasStrike = false;

        Collections.shuffle(potentialTargets, AbstractDungeon.cardRandomRng.random);

        for (AbstractCard c: potentialTargets) {
            if (c.type == AbstractCard.CardType.ATTACK){
                validTargets.add(c);
                if (c.hasTag(AbstractCard.CardTags.STRIKE)) hasStrike = true;

                if (validTargets.size() >= 1){
                    break;
                }
            }
        }

        for (AbstractCard c: validTargets){
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                AbstractDungeon.player.createHandIsFullDialog();
            } else {
                c.unhover();
                c.lighten(true);
                c.setAngle(0.0F);
                c.drawScale = 0.12F;
                c.targetDrawScale = 0.75F;
                c.current_x = CardGroup.DRAW_PILE_X;
                c.current_y = CardGroup.DRAW_PILE_Y;
                AbstractDungeon.player.drawPile.removeCard(c);
                AbstractDungeon.player.hand.addToTop(c);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }
        }

        if (hasStrike){
            addToBot(new GainEnergyAction(1));
        }


        this.isDone = true;
    }


}