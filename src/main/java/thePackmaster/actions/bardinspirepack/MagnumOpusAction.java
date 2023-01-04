package thePackmaster.actions.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class MagnumOpusAction extends AbstractGameAction
{
    private UUID uuid;
    private int miscIncrease;

    public MagnumOpusAction(UUID targetUUID, int miscValue, int miscIncrease)
    {
        uuid = targetUUID;
        this.miscIncrease = miscIncrease;
    }

    @Override
    public void update()
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(uuid)) {
                c.misc += miscIncrease;
                c.applyPowers();
            }
        }
        for (AbstractCard c : GetAllInBattleInstances.get(uuid)) {
            c.misc += miscIncrease;
            c.applyPowers();
        }
        isDone = true;
    }
}
