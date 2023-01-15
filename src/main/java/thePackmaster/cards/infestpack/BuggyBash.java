package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class BuggyBash extends AbstractInfestCard {
    public final static String ID = makeID("BuggyBash");
    // intellij stuff attac, enemy, common, 14, 1, , , 2, 1

    public BuggyBash() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = magicNumber = 4;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * InfestModifier.getInfestCount(this);
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int count = InfestModifier.getInfestCount(this);
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * count;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}