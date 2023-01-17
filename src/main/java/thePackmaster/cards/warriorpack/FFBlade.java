package thePackmaster.cards.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.warriorpack.FastDamage;
import thePackmaster.cardmodifiers.warriorpack.FeralDamage;
import thePackmaster.cardmodifiers.warriorpack.FrontDamage;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FFBlade extends AbstractPackmasterCard {

    public final static String ID = makeID(FFBlade.class.getSimpleName());

    private static final int COST = 1;

    public FFBlade(){
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        DamageModifierManager.addModifier(this, new FastDamage());
        DamageModifierManager.addModifier(this, new FeralDamage());
        DamageModifierManager.addModifier(this, new FrontDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}
