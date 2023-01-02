package thePackmaster.actions.witchesstrikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.AbstractPackMasterOrb;

public class MoonlightBarrageAction extends AbstractGameAction
{
    private DamageInfo info;
    private AbstractPlayer p;
    public MoonlightBarrageAction(DamageInfo info)
    {
        this.info = info;
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    public void update()
    {
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            for (AbstractOrb orb : AbstractDungeon.player.orbs){
                if (!(orb instanceof EmptyOrbSlot)){
                    addToBot(new DamageRandomEnemyAction(info,AttackEffect.FIRE));
                    if (orb instanceof AbstractPackMasterOrb) {
                        for (int i = 0; i < this.amount; ++i) {
                            ((AbstractPackMasterOrb)orb).PassiveEffect();
                        }
                    } else {
                        for (int i = 0; i < this.amount; ++i) {
                            orb.onStartOfTurn();
                            orb.onEndOfTurn();
                        }
                    }
                }
            }
        }
        isDone = true;
    }
}
