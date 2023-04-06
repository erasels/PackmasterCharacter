package thePackmaster.actions.lockonpack;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class LightsOutAction
    extends AbstractGameAction
{
    boolean upgraded = false;

    public LightsOutAction(boolean evoke)
    {
        super();
        upgraded = evoke;
    }

    @Override
    public void update() {
        ArrayList<AbstractOrb> orbs = Wiz.adp().orbs;
        for (int i = 0; i < orbs.size(); i++)
        {
            if (orbs.get(i) instanceof Lightning)
            {
                if (upgraded)
                {
                    addToTop(new EvokeSpecificOrbAction(orbs.get(i)));
                    addToBot(new ChannelAction(new Dark()));
                }
                else
                {
                    Dark d = new Dark();
                    orbs.set(i, d);
                    d.setSlot(i, Wiz.adp().maxOrbs);
                }
            }
        }

        isDone = true;
    }
}
