package thePackmaster.cardmodifiers.creativitypack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.creativitypack.AccumulativeStrike;
import thePackmaster.util.creativitypack.JediUtil;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AccumulativeDamageModifier extends AbstractCardModifier {
    public static final String ID = makeID(AccumulativeDamageModifier.class.getSimpleName());

    private int damageRamp;

    public AccumulativeDamageModifier(int dmg)
    {
        damageRamp = dmg;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        float retVal = damage;
        retVal += JediUtil.cardsCreatedThisCombat.size() * damageRamp;
        return retVal;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AccumulativeDamageModifier(damageRamp);
    }

    public void setDamageRamp(int damageRamp) {
        this.damageRamp = damageRamp;
    }
}
