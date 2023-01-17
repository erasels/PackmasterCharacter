package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SwordAndBoardAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCreature target;
    private int blockGain;
    
    public SwordAndBoardAction(AbstractCreature target, DamageInfo info, int blockGain) {
        this.info = info;
        this.target = target;
        this.blockGain = blockGain;
    }
    
    public void update() {
        if ((AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1) % 2 == 1) {
            this.addToTop(new DamageAction(this.target, this.info, AttackEffect.SLASH_HORIZONTAL));
        }
        else
        {
            this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockGain));
        }
        this.isDone = true;
    }
}
