package thePackmaster.actions.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EnchantmentAction extends AbstractGameAction {

    AbstractCard toPlay;
    public EnchantmentAction(AbstractCard card, AbstractCreature newTarget)
    {
        this.actionType = ActionType.CARD_MANIPULATION;// 21
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        toPlay = card;
        target = newTarget;
    }
    @Override
    public void update() {
        toPlay.purgeOnUse = true;
        toPlay.setCostForTurn(0);
        AbstractDungeon.player.limbo.group.add(toPlay);
        toPlay.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
        toPlay.target_y = (float)Settings.HEIGHT / 2.0F;
        toPlay.targetAngle = 0.0F;
        toPlay.lighten(false);
        toPlay.drawScale = 0.12F;
        toPlay.targetDrawScale = 0.75F;
        toPlay.applyPowers();
        toPlay.setCostForTurn(0);
        addToTop(new NewQueueCardAction(toPlay, this.target, false, true));
        addToTop(new UnlimboAction(toPlay));
        if (!Settings.FAST_MODE) {// 55
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));// 56
        } else {
            addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));// 58
        }
        isDone = true;
    }
}
