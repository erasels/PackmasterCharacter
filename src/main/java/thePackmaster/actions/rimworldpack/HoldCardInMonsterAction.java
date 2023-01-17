package thePackmaster.actions.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.rimworldpack.HoldCardPower;

public class HoldCardInMonsterAction extends AbstractGameAction {
    private AbstractCreature owner;

    private float startingDuration;

    private AbstractCard card;

    //Copied and trimmed from the automaton summons
    public HoldCardInMonsterAction(AbstractCreature monsterTarget, AbstractCard card) {
        owner = monsterTarget;
        duration = startingDuration = Settings.ACTION_DUR_LONG;
        actionType = AbstractGameAction.ActionType.WAIT;
        this.card = card;
    }

    public void update() {
        if (duration == startingDuration) {
            if(AbstractDungeon.player.drawPile.contains(card))
                AbstractDungeon.player.drawPile.removeCard(card);
            else if(AbstractDungeon.player.discardPile.contains(card))
                AbstractDungeon.player.discardPile.removeCard(card);
            else if(AbstractDungeon.player.hand.contains(card))
                AbstractDungeon.player.hand.removeCard(card);
            else {
                isDone = true;
                return;
            }

            AbstractDungeon.player.limbo.addToBottom(card);
            card.setAngle(0.0F);
            card.targetDrawScale = 0.75F;
            card.target_x = Settings.WIDTH / 2.0F;
            card.target_y = Settings.HEIGHT / 2.0F;
            card.lighten(false);
            card.unfadeOut();
            card.unhover();
            card.untip();
            card.stopGlowing();
        }
        tickDuration();
        if (isDone && card != null) {
            addToTop(new ApplyPowerAction(owner, owner, new HoldCardPower(owner, card)));
            addToTop(new ShowCardAction(card));
        }
    }
}