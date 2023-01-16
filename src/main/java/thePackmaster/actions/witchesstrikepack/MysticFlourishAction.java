package thePackmaster.actions.witchesstrikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.orbs.PackmasterOrb;

public class MysticFlourishAction extends AbstractGameAction
{
    private AbstractPlayer p;
    public MysticFlourishAction(int count)
    {
        amount = count;
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    public void update()
    {
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            if (AbstractDungeon.player.orbs.get(0) instanceof PackmasterOrb) {
                for (int i = 0; i < this.amount; i++) {
                    ((PackmasterOrb) AbstractDungeon.player.orbs.get(0)).passiveEffect();
                }
                isDone = true;
            } else {
                for (int i = 0; i < this.amount; i++) {
                    AbstractDungeon.player.orbs.get(0).onStartOfTurn();
                    AbstractDungeon.player.orbs.get(0).onEndOfTurn();
                }
                isDone = true;
            }
        } else {
            isDone = true;
        }
    }
}
