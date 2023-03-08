package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bitingcoldpack.IceShatterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class IceShatter extends BitingColdCard {
    // TODO: add art lmao
    public final static String ID = makeID("IceShatter");

    public IceShatter() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, new IceShatterPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(3);
    }
}
