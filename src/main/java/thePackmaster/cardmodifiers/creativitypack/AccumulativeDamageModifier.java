package thePackmaster.cardmodifiers.creativitypack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.creativitypack.JediUtil;

public class AccumulativeDamageModifier extends AbstractCardModifier {

    private final int damageRamp;

    public AccumulativeDamageModifier(int dmg)
    {
        damageRamp = dmg;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        float retVal = damage;
        retVal += JediUtil.cardsCreatedThisCombat.stream().filter(c -> c.type != AbstractCard.CardType.STATUS || card.upgraded).count() * damageRamp;
        return retVal;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AccumulativeDamageModifier(damageRamp);
    }
}
