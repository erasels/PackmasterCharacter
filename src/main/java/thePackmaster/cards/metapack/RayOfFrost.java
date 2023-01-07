package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.metapack.RayOfFrostPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.MAGIC;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RayOfFrost extends AbstractPackmasterCard {
    public final static String ID = makeID("RayOfFrost");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int FROST_AMOUNT = 1;

    public RayOfFrost() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = FROST_AMOUNT;
        tags.add(MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.applyToSelf(new RayOfFrostPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
