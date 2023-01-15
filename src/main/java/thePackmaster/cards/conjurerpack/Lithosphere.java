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
    public final static String ID = makeID("Lithosphere");
    private static final int BLOCK = 7;

    public Lithosphere() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new PetraPower(m,1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
