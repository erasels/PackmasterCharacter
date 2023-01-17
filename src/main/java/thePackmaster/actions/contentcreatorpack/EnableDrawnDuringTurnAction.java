package thePackmaster.actions.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import thePackmaster.patches.contentcreatorpack.PreDrawPatch;

public class EnableDrawnDuringTurnAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        PreDrawPatch.DRAWN_DURING_TURN = true;
    }
}
