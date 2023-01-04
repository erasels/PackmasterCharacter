package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bees extends AbstractPackmasterCard implements OnInfestCard {
    public final static String ID = makeID("Bees");
    // intellij stuff attack, enemy, rare, 1, , , , 8, 2

    public Bees() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 8;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, getRandomAttackEffect());
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += InfestModifier.getInfestCount(this);
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int count = InfestModifier.getInfestCount(this);
        int realBaseDamage = this.baseDamage;
        this.baseDamage += count;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    private static AbstractGameAction.AttackEffect getRandomAttackEffect() {
        int result = MathUtils.random(0, 8);
        switch (result) {
            case 0:
                return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
            case 1:
                return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
            case 2:
                return AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
            case 3:
                return AbstractGameAction.AttackEffect.SMASH;
            case 4:
                return AbstractGameAction.AttackEffect.SLASH_HEAVY;
            case 5:
                return AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
            case 6:
                return AbstractGameAction.AttackEffect.SLASH_VERTICAL;
            case 7:
                return AbstractGameAction.AttackEffect.POISON;
            case 8:
                return AbstractGameAction.AttackEffect.FIRE;
            default:
                return AbstractGameAction.AttackEffect.NONE;
        }
    }

    @Override
    public void onInfest(int infestCounter) {
        StringBuilder sb = new StringBuilder();
        sb.append(cardStrings.EXTENDED_DESCRIPTION[0]);
        for (int i = 0; i < infestCounter + 2; i++) {
            sb.append(cardStrings.EXTENDED_DESCRIPTION[1]);
        }
        sb.append(cardStrings.EXTENDED_DESCRIPTION[2]);
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}