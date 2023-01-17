package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.psychicpack.ExplodingPoleEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TelephonePole extends LockingCard {
    public final static String ID = makeID("TelephonePole");
    // intellij stuff attack, enemy, common, 13, 3, , , 2, 

    public TelephonePole() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);

        atb(new VFXAction(new ExplodingPoleEffect(m, p), 0.4f));
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
    }

    public void upp() {
        upgradeDamage(4);
    }
}