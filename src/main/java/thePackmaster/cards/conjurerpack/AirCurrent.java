package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.applyToEnemy;

public class AirCurrent extends ConjurerCard {
    public final static String ID = makeID(AirCurrent.class);
    private static final int DAMAGE = 5;
    private static final int MAGIC = 1;

    public AirCurrent() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (m != null && m.hasPower(PetraPower.POWER_ID)) {
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        }
    }


    @Override
    public void triggerOnGlowCheck() {
        glowColor = Wiz.getEnemies().stream().anyMatch(q -> q.hasPower(PetraPower.POWER_ID)) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(3);
    }
}
