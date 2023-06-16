package thePackmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

public class PlayFromDiscardAction extends AbstractGameAction {

    private AbstractCard toPlay;

    public PlayFromDiscardAction(AbstractCard toPlay) {
        this.toPlay = toPlay;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractMonster targetFoe = Wiz.getRandomEnemy();
            AbstractDungeon.player.discardPile.group.remove(toPlay);
            AbstractDungeon.getCurrRoom().souls.remove(toPlay);
            AbstractDungeon.player.limbo.group.add(toPlay);
            toPlay.current_y = -200.0F * Settings.scale;
            toPlay.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            toPlay.target_y = (float) Settings.HEIGHT / 2.0F;
            toPlay.targetAngle = 0.0F;
            toPlay.lighten(false);
            toPlay.drawScale = 0.12F;
            toPlay.targetDrawScale = 0.75F;
            toPlay.applyPowers();
            this.addToTop(new NewQueueCardAction(toPlay, targetFoe, false, true));
            this.addToTop(new UnlimboAction(toPlay));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

            this.isDone = true;
        }

    }
}