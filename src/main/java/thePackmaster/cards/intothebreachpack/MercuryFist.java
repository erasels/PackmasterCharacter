package thePackmaster.cards.intothebreachpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class MercuryFist extends IntoTheBreachCard {
    public final static String ID = makeID("MercuryFist");

    public MercuryFist() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 12;
        magicNumber = baseMagicNumber = 3;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.OLIVE.cpy()));
        atb(new WaitAction(0.8F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(6);
    }
}
