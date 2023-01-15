package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.powers.intothebreachpack.AcidPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class AirCurrent extends ConjurerCard
{
    public final static String ID = makeID("AirCurrent");
    private static final int DAMAGE = 5;

    public AirCurrent() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (m != null && m.hasPower(PetraPower.POWER_ID))
        {
            applyToEnemy(m, new VulnerablePower(m,1, false));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}
