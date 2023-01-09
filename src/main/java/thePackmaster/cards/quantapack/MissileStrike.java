package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MissileStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("MissileStrike");

    private static final int DAMAGE = 8;
    private static final int SPLASH = 4;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int UPGRADE_SPLASH = 1;

    public MissileStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = SPLASH;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_SPLASH);
    }
}
