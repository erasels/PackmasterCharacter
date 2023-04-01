package thePackmaster.cardmodifiers.lockonpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.util.Wiz;

public class GlockOnModifier extends AbstractCardModifier {

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + Wiz.getLogicalPowerAmount(AbstractDungeon.player, FocusPower.POWER_ID);
    }
    @Override
    public float modifyDamageFinal(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if (target == null) return super.modifyDamage(damage, type, card, target);
        LockOnPower pow = (LockOnPower) target.getPower(LockOnPower.POWER_ID);
        if (pow == null)    return super.modifyDamage(damage, type, card, target);

        float leftover = damage - (int) damage; //This shit can't be fixed
        return AbstractOrb.applyLockOn(target, (int)damage) + leftover;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GlockOnModifier();
    }
}
