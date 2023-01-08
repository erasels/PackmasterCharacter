package thePackmaster.actions.entropypack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UnbindAction extends AbstractGameAction {
    private final DamageInfo info;

    public UnbindAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                this.addToTop(new DamageAction(this.target, this.info, MathUtils.randomBoolean() ? AttackEffect.SLASH_HORIZONTAL : AttackEffect.SLASH_VERTICAL, true));
                this.addToTop(new DiscardSpecificCardAction(c));
            }
        }

        this.isDone = true;
    }
}
