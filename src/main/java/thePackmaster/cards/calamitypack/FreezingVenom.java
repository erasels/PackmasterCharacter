package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

public class FreezingVenom extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FreezingVenom");
    private static final int COST = 2;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int FROSTBITE_POISON = 3;
    private static final int UPGRADE_FROSTBITE_POISON = 1;

    public FreezingVenom() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = FROSTBITE_POISON;
        this.isMultiDamage = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        this.upgradeMagicNumber(UPGRADE_FROSTBITE_POISON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.POISON));
        this.addToBot(new AllEnemyApplyPowerAction(p, this.magicNumber, m -> new FrostbitePower(m, this.magicNumber)));
        this.addToBot(new AllEnemyApplyPowerAction(p, this.magicNumber, m -> new PoisonPower(m, p, this.magicNumber)));
    }
}
