package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class TectonicQuake extends ConjurerCard
{
    public final static String ID = makeID("SandAttack");
    private static final int DAMAGE = 27;
    private static final int MAGIC = 1;

    public TectonicQuake() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying) {
                this.addToBot(new ApplyPowerAction(m, p, new PetraPower(m, this.magicNumber)));
                this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
            }
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}
