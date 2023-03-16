package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.util.Wiz;

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
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        this.upgradeMagicNumber(UPGRADE_FROSTBITE_POISON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        for (AbstractMonster m : Wiz.getEnemies()) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
            this.addToBot(new ApplyPowerAction(m, p, new FrostbitePower(m, this.magicNumber)));
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber)));
        }
    }
}
