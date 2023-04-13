package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.powers.dimensiongate2pack.SchemeNextTurnPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Scheme extends AbstractDimensionalCardVault {
    public final static String ID = makeID("Scheme");

    public Scheme() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
         ExhaustiveVariable.setBaseValue(this, 2);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SchemeNextTurnPower(p, 1));
    }

    public void upp() {
        ExhaustiveVariable.upgrade(this, 1);

    }
}