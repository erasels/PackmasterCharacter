package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class RagingInferno extends ConjurerCard
{
    public final static String ID = makeID("RagingInferno");
    private static final int DAMAGE = 35;
    private static final int MAGIC = 6;

    public RagingInferno() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new IgnitePower(m, MAGIC));
    }

    public void upp() {
        exhaust = false;
    }
}
