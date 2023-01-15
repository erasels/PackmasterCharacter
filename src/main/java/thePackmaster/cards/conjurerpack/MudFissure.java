package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.conjurerpack.PetraPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class MudFissure extends ConjurerCard
{
    public final static String ID = makeID(MudFissure.class);
    private static final int DAMAGE = 7;

    public MudFissure() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

        if (mo != null && mo.powers != null && mo.powers.stream().anyMatch(po -> po.type == AbstractPower.PowerType.DEBUFF)) {
            this.damage *= 2;
        }

        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(3);
    }
}
