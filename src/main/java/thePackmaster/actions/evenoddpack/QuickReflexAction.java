package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class QuickReflexAction extends AbstractGameAction {
    private int blockGain;
    
    public QuickReflexAction(int blockGain) {
        this.blockGain = blockGain;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockGain));
        }
        this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockGain));
        this.isDone = true;
    }
}
