package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.powers.dimensiongate2pack.SchemeNextTurnPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Scheme extends AbstractDimensionalCardGrift {
    public final static String ID = makeID("Scheme");

    public Scheme() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SchemeNextTurnPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);

    }
}