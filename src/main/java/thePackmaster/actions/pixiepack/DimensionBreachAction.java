package thePackmaster.actions.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.packs.PixiePack;

public class DimensionBreachAction extends AbstractGameAction {
    AbstractCreature target;
    public DimensionBreachAction(AbstractMonster abstractMonster)
    {
        this.actionType = ActionType.DAMAGE;// 21
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        target = abstractMonster;
    }
    @Override
    public void update() {
        AbstractCard toPlay = PixiePack.pixieGenerate(null,null, AbstractCard.CardType.ATTACK,null);
        toPlay.purgeOnUse = true;
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
