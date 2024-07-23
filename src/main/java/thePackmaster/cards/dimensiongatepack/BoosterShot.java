package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardEden;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoosterShot extends AbstractDimensionalCardEden {
    public final static String ID = makeID("BoosterShot");

    public BoosterShot() {
        super(ID, 0, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 8;
        baseSecondMagic = secondMagic = 10;
        baseDamage = 1;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        Wiz.atb(new HealAction(m, p, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(-2);
    }
}