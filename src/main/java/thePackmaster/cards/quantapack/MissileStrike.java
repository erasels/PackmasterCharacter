package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MissileStrike extends AbstractQuantaCard {
    public final static String ID = makeID("MissileStrike");

    private static final int DAMAGE = 8;
    private static final int SPLASH = 4;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int UPGRADE_SPLASH = 1;

    public MissileStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseSecondDamage = SPLASH;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(baseSecondDamage), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SPLASH);
    }
}
