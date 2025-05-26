package thePackmaster.actions.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.rimworldpack.HoldCardPower;
import thePackmaster.util.Wiz;

public class PutCardInMonsterAction extends AbstractGameAction {

    AbstractCard card;
    public PutCardInMonsterAction(AbstractCard card, AbstractMonster target){
        this.target = target;
        this.card = card;
    }

    @Override
    public void update() {
        if(target.isDying || target.isDeadOrEscaped() || target.currentHealth <= 0)
        {
            isDone = true;
            return;
        }
        addToTop(new ApplyPowerAction(target, target, new HoldCardPower(target, card)));
        card.purgeOnUse = true;
        isDone = true;
    }
}
