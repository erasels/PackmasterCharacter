package thePackmaster.actions.witchesstrikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.PackmasterOrb;

public class MoonlightBarrageAction extends AbstractGameAction
{
    private AbstractCard card;
    private AttackEffect effect;
    private AbstractPlayer p;
    public MoonlightBarrageAction(AbstractCard card, AttackEffect effect)
    {
        amount = 1;
        this.card = card;
        this.effect = effect;
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    public void update()
    {
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            for (AbstractOrb orb : AbstractDungeon.player.orbs){
                if (!(orb instanceof EmptyOrbSlot)){
                    addToBot(new AttackDamageRandomEnemyAction(card,effect));
                    if (orb instanceof PackmasterOrb) {
                        for (int i = 0; i < this.amount; ++i) {
                            ((PackmasterOrb)orb).passiveEffect();
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
