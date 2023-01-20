package thePackmaster.actions.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import thePackmaster.patches.contentcreatorpack.DisableCountingStartOfTurnDrawPatch;

public class EnableDrawnDuringTurnAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        DisableCountingStartOfTurnDrawPatch.DRAWN_DURING_TURN = true;
    }
}
