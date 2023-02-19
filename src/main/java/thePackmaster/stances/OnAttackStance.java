package thePackmaster.stances;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract interface OnAttackStance {

    public abstract void onAttack(DamageInfo info, int damageAmount, AbstractCreature target);

}
