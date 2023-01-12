package thePackmaster.orbs.summonspack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public interface OnApplyPowerOrb {
    void onApplyPower(AbstractCreature target, AbstractPower power);
}
