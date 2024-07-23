package thePackmaster.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IncreaseDamageModifier extends AbstractCardModifier {
    int amount;

    public IncreaseDamageModifier() {
        amount = 1;
    }

    public IncreaseDamageModifier(int amt) {
        amount = amt;
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + amount;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new IncreaseDamageModifier(amount);
    }
}