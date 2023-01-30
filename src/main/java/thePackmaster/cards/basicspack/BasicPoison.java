package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.basicspack.BasicPoisonPower;
import thePackmaster.powers.basicspack.BasicSupplyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicPoison extends AbstractPackmasterCard {
    public final static String ID = makeID("BasicPoison");

    public BasicPoison() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, "basics");
        this.baseDamage = this.damage = 7;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        addToBot(new ApplyPowerAction(p, p, new BasicPoisonPower(this.magicNumber)));
    }

    public void upp(){
        upgradeMagicNumber(2);
    }
}
