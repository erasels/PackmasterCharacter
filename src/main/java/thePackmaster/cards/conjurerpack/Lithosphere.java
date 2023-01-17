package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.intothebreachpack.IntoTheBreachCard;
import thePackmaster.powers.conjurerpack.PetraPower;
import thePackmaster.powers.intothebreachpack.AcidPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Lithosphere extends ConjurerCard
{
    public final static String ID = makeID(Lithosphere.class);
    private static final int BLOCK = 7;
    private static final int MAGIC = 1;

    public Lithosphere() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new PetraPower(m, magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
