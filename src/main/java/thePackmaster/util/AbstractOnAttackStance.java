package thePackmaster.util;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract class AbstractOnAttackStance extends AbstractStance {

    public abstract void onAttack(DamageInfo info, int damageAmount, AbstractCreature target);

}
