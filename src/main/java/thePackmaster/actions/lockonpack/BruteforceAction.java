package thePackmaster.actions.lockonpack;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.orbs.Lightning;

public class BruteforceAction
    extends AbstractGameAction
{
    public static boolean aoeLightning = false;
    private int evokeCount = 0;

    public BruteforceAction (int evokeCount)
    {
        this.evokeCount = evokeCount;
    }
    @Override
    public void update()
    {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                aoeLightning = true;
                isDone = true;
            }
        });
        Lightning l = (Lightning) EvokeWithoutRemovingSpecificOrbAction.getRightmostOrb(Lightning.ORB_ID);
        if (l != null)
        {
            addToBot(new EvokeWithoutRemovingSpecificOrbAction(l, evokeCount - 1));
            addToBot(new EvokeSpecificOrbAction(l));
        }

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                aoeLightning = false;
                isDone = true;
            }
        });
        isDone = true;
    }
}
