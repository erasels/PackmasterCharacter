package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WindupAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCreature target;
    private AbstractCard card;
    
    public WindupAction(AbstractCreature target, DamageInfo info, int amount, AbstractCard card) {
        this.info = info;
        this.target = target;
        this.amount = amount;
        this.card = card;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1) {
            this.addToTop(new DamageAction(this.target, this.info, AttackEffect.SLASH_HORIZONTAL));
        }
        else
        {
            this.addToTop(new ModifyDamageAction(card.uuid, amount));
        }
        this.isDone = true;
    }
}
