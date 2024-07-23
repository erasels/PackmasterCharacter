package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AddDamageModifier extends AbstractMadScienceModifier {

    public AddDamageModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + value;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddDamageModifier(value);
    }
}