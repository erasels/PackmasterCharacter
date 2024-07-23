package thePackmaster.actions.witchesstrikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class ManifestAction extends AbstractGameAction
{
    private AbstractOrb coalesceOrb;
    private AbstractPlayer p;
    public ManifestAction(AbstractOrb newOrbType)
    {
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_FAST;
        coalesceOrb = newOrbType;
        p = AbstractDungeon.player;
    }

    public void update()
    {
        if (p.maxOrbs < 10) {
            p.maxOrbs += 1;
            p.orbs.add(new EmptyOrbSlot());
            for (int i = 0; i < p.orbs.size(); ++i) {
                p.orbs.get(i).setSlot(i, p.maxOrbs);
            }
        }
        addToBot(new ChannelAction(coalesceOrb, false));
        isDone = true;
    }
}
