package thePackmaster.cardmodifiers.instadeathpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageMultiplierModifier extends AbstractCardModifier {
    private final int multiplier;

    public DamageMultiplierModifier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public float modifyDamageFinal(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage * multiplier;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageMultiplierModifier(multiplier);
    }
}
