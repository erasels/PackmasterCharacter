package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class Anemia extends AbstractDimensionalCardVault {
    public final static String ID = makeID("Anemia");

    public Anemia() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = 9;
        baseMagicNumber = magicNumber = 1;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(this::applyEffect);
    }

    public void applyEffect(AbstractMonster q) {
        if (q.hasPower(PoisonPower.POWER_ID)) {
            applyToEnemy(q, new WeakPower(q, magicNumber, false));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = Wiz.getEnemies().stream().anyMatch(q -> q.hasPower(PoisonPower.POWER_ID)) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);

    }
}