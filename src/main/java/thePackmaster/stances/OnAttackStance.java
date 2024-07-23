package thePackmaster.stances;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface OnAttackStance {
    void onAttack(DamageInfo info, int damageAmount, AbstractCreature target);
}
